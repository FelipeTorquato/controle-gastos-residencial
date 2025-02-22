package com.controle.controle_gastos.domain.exceptions;

import lombok.Getter;

@Getter
public class DomainException extends Exception {

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
