package com.controle.controle_gastos.domain.exceptions;

/**
 * Represents the error codes for the application.
 */
public enum ErrorCode {
  /**
   * Used when invalid parameters are passed to the server.
   */
  INVALID_PARAMS,

  /**
   * Used for validation when a name is already taken.
   */
  NAME_ALREADY_EXISTS,

  /**
   * Used when a user is not found.
   */
  USER_NOT_FOUND,

  /**
   * Used when a transaction is not found.
   */
  TRANSACTION_NOT_FOUND,

  /**
   * Used when a MINOR tries to create a transaction of type REVENUE.
   */
  MINOR_CANT_CREATE_REVENUE
}
