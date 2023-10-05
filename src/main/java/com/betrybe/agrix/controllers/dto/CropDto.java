package com.betrybe.agrix.controllers.dto;

import java.time.LocalDate;

/**
 * javadoc.
 */
public record CropDto(
    Long id,
    String name,
    double plantedArea,
    Long farmId,
    LocalDate plantedDate,
    LocalDate harvestDate
) {}
