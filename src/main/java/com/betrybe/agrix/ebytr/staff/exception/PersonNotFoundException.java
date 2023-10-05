package com.betrybe.agrix.ebytr.staff.exception;

/**
 * javadoc.
 */
public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }

}
