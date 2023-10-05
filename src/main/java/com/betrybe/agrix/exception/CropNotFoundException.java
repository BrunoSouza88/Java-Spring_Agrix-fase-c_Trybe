package com.betrybe.agrix.exception;

/**
 * javadoc.
 */
public class CropNotFoundException extends RuntimeException {

  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
