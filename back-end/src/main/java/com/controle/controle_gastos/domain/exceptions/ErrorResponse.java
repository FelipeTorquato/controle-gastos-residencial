package com.controle.controle_gastos.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class used by the GlobalHandlerException to return the error message and code to the client.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  private String message;
  private ErrorCode code;
}
