package com.controle.controle_gastos.domain.exceptions;

import lombok.Getter;

/**
 * This class is used to handle exceptions thrown by the domain layer, like validation errors.
 */
@Getter
public class DomainException extends Exception {

  /**
   * This error code is the ErrorCode enum.
   */
  private final ErrorCode code;

  public DomainException(ErrorCode code) {
    super("Error Code: " + code.toString());
    this.code = code;
  }

  public DomainException(String message, ErrorCode code) {
    super(message);
    this.code = code;
  }
}
