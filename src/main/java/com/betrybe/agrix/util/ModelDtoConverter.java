package com.betrybe.agrix.util;

import com.betrybe.agrix.controllers.dto.CropCreationDto;
import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmCreationDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.controllers.dto.FertilizerCreationDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.controllers.dto.PersonCreationDto;
import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * javadoc.
 */
public class ModelDtoConverter {

  /**
   * javadoc.
   */
  public static FarmDto farmToFarmDto(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * javadoc.
   */
  public static Farm farmCreationDtoToFarm(FarmCreationDto farmCreationDto) {
    Farm farm = new Farm();

    farm.setName(farmCreationDto.name());
    farm.setSize(Double.parseDouble(farmCreationDto.size()));

    return farm;
  }

  /**
   * javadoc.
   */
  public static Crop cropCreationDtoToCrop(CropCreationDto cropCreationDto) {
    Crop crop = new Crop();
    crop.setName(cropCreationDto.name());
    crop.setPlantedArea(cropCreationDto.plantedArea());
    crop.setPlantedDate(cropCreationDto.plantedDate());
    crop.setHarvestDate(cropCreationDto.harvestDate());
    return crop;
  }

  /**
   * javadoc.
   */
  public static CropDto cropToCropDto(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }

  /**
   * javadoc.
   */
  public static Fertilizer fertilizerCreationDtoToFertilizer(
      FertilizerCreationDto fertilizerCreationDto
  ) {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(fertilizerCreationDto.name());
    fertilizer.setBrand(fertilizerCreationDto.brand());
    fertilizer.setComposition(fertilizerCreationDto.composition());
    return fertilizer;
  }

  /**
   * javadoc.
   */
  public static FertilizerDto fertilizerToFertilizerDto(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  /**
   * javadoc.
   */
  public static Person personCreationDtoToPerson(
      PersonCreationDto personCreationDto
  ) {
    Person person = new Person();
    person.setUsername(personCreationDto.username());
    person.setPassword(personCreationDto.password());
    person.setRole(personCreationDto.role());
    return person;
  }

  /**
   * javadoc.
   */
  public static PersonDto personToPersonDto(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
