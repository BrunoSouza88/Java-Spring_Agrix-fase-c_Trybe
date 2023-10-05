package com.betrybe.agrix.controllers.dto;

import java.time.LocalDate;

/**
 * javadoc.
 */
public record CropCreationDto(
    String name, double plantedArea, LocalDate plantedDate, LocalDate harvestDate
) {}
