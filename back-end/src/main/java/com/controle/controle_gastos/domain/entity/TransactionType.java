package com.controle.controle_gastos.domain.entity;

/**
 * Represents the type of transaction.
 */
public enum TransactionType {
  /**
   * Only ADULT users can make REVENUE transactions.
   */
  REVENUE,
  /**
   * All users can make EXPENSE transactions.
   */
  EXPENSE
}
