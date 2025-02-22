package com.controle.controle_gastos.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Used to return rest error messages to the client.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestError {

  private String field;
  private String message;
}
