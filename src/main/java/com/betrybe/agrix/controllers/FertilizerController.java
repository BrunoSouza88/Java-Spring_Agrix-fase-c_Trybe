package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerCreationDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import com.betrybe.agrix.util.ModelDtoConverter;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * javadoc.
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(
      @RequestBody FertilizerCreationDto fertilizerCreationDto
  ) {
    Fertilizer fertilizer = fertilizerService.create(
        ModelDtoConverter.fertilizerCreationDtoToFertilizer(fertilizerCreationDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(
        ModelDtoConverter.fertilizerToFertilizerDto(fertilizer)
    );
  }

  /**
   * javadoc.
   */
  @GetMapping()
  @Secured({"ADMIN"})
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<FertilizerDto> fertilizerDtos = fertilizerService.getAllFertilizers()
        .stream().map((fertilizer) -> new FertilizerDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        )).collect(Collectors.toList());
    return ResponseEntity.ok().body(fertilizerDtos);
  }

  /**
   * javadoc.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(id);
    return ResponseEntity.ok().body(
        ModelDtoConverter.fertilizerToFertilizerDto(fertilizer)
    );
  }

}
