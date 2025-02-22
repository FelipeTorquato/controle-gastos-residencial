package com.controle.controle_gastos.application.filter;

import com.controle.controle_gastos.domain.exceptions.RestError;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestResponseError {

  private String code;

  private String message;

  private List<RestError> restErrors;

  public RestResponseError(String code) {
    this.code = code;
    this.message = "Error Code: " + code;
    this.restErrors = new ArrayList<>();
  }

  public void addError(RestError restError) {
    restErrors.add(restError);
  }
}
