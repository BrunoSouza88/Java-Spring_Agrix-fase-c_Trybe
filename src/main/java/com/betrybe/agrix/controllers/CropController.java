package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FertilizerService;
import com.betrybe.agrix.util.ModelDtoConverter;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * javadoc.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final CropService cropService;

  private final FertilizerService fertilizerService;

  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * javadoc.
   */
  @GetMapping()
  @Secured({"ADMIN", "MANAGER"})
  public ResponseEntity<List<CropDto>> getAllcrops() {
    List<Crop> allCrop = cropService.getAllCrops();
    List<CropDto> allCropDto = allCrop.stream()
        .map((crop) -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()
        )).collect(Collectors.toList());
    return ResponseEntity.ok(allCropDto);
  }

  /**
   * javadoc.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);
    return ResponseEntity.ok(ModelDtoConverter.cropToCropDto(crop));
  }

  /**
   * javadoc.
   */
  @GetMapping("search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDateRange(
      @RequestParam("start") String start,
      @RequestParam("end") String end
  ) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    List<Crop> crops = cropService.findCropsByHarvestDateBetween(startDate, endDate);
    List<CropDto> cropDtos = crops.stream()
        .map((crop) -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()
        )).collect(Collectors.toList());
    return  ResponseEntity.ok(cropDtos);
  }

  /**
   * javadoc.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropAndFertilizer(
      @PathVariable Long cropId, @PathVariable Long fertilizerId
  ) {
    Crop crop = cropService.getCropById(cropId);
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);
    cropService.insertCrop(crop);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * javadoc.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> fertilizersAssociatedWithTheCropById(
      @PathVariable Long cropId
  ) {
    Crop crop = cropService.getCropById(cropId);
    List<Fertilizer> fertilizers = crop.getFertilizers();
    return ResponseEntity.ok().body(fertilizers);
  }
}
