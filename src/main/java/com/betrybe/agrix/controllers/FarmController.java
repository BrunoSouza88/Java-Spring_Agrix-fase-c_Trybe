package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropCreationDto;
import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmCreationDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import com.betrybe.agrix.util.ModelDtoConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * javadoc.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  private final CropService cropService;

  /**
   * javadoc.
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * javadoc.
   */
  @PostMapping()
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmCreationDto farmDto) {
    Farm newFarm = farmService.insertFarm(ModelDtoConverter.farmCreationDtoToFarm(farmDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(ModelDtoConverter.farmToFarmDto(newFarm));
  }

  /**
   * javadoc.
   */
  @GetMapping()
  @Secured({"ADMIN", "USER", "MANAGER"})
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    return allFarms.stream()
        .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * javadoc.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);
    return ResponseEntity.ok().body(ModelDtoConverter.farmToFarmDto(farm));
  }

  /**
   * javadoc.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId, @RequestBody CropCreationDto cropCreationDto
  ) {
    Farm farm = farmService.getFarmById(farmId);
    Crop crop = ModelDtoConverter.cropCreationDtoToCrop(cropCreationDto);

    crop.setFarm(farm);

    Crop newCrop = cropService.insertCrop(crop);

    farm.getCrops().add(crop);
    farmService.insertFarm(farm);

    CropDto cropDto = ModelDtoConverter.cropToCropDto(newCrop);

    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
  }

  /**
   * javadoc.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> allCropsByFarmId(
      @PathVariable Long farmId
  ) {
    Farm farm = farmService.getFarmById(farmId);
    List<Crop> crops = farm.getCrops();
    List<CropDto> cropsDto = crops.stream()
        .map((crop) ->
            new CropDto(
                crop.getId(),
                crop.getName(),
                crop.getPlantedArea(),
                crop.getFarm().getId(),
                crop.getPlantedDate(),
                crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropsDto);
  }
}
