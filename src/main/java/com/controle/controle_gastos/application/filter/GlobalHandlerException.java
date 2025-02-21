package com.controle.controle_gastos.application.filter;

import com.controle.controle_gastos.domain.exceptions.DomainException;
import com.controle.controle_gastos.domain.exceptions.ErrorCode;
import com.controle.controle_gastos.domain.exceptions.ErrorResponse;
import com.controle.controle_gastos.domain.exceptions.RestError;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

  Logger logger = LogManager.getLogger(GlobalHandlerException.class);

  private final MessageSource messageSource;

  public GlobalHandlerException(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

//  @ExceptionHandler(DomainException.class)
//  public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
//    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getCode());
//    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
//  }

  @ExceptionHandler(value = {DomainException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse domainException(DomainException ex) {
    logger.error(ex.getMessage());

    // Log da causa do erro, se existir
    Throwable cause = ex.getCause();
    if (cause != null) {
      logger.error("Causa do erro: ", cause);
    }

    // Retorna a resposta de erro usando ErrorResponse
    return new ErrorResponse(ex.getMessage(), ex.getCode());
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public RestResponseError handle(MethodArgumentNotValidException exception) {
    logger.error(exception.getMessage());

    RestResponseError err = new RestResponseError(ErrorCode.INVALID_PARAMS.name());
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

    fieldErrors.forEach(e -> {
      String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
      err.addError(new RestError(e.getField(), message));
    });

    return err;
  }
}
