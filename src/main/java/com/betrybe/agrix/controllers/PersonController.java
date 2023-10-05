package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.PersonCreationDto;
import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.util.ModelDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * javadoc.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * javadoc.
   */
  @PostMapping()
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonCreationDto personCreationDto) {
    Person person = personService.create(
        ModelDtoConverter.personCreationDtoToPerson(personCreationDto));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ModelDtoConverter.personToPersonDto(person));
  }
}
