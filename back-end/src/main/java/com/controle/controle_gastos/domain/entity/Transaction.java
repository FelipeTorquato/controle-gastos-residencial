package com.controle.controle_gastos.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a transaction in the system. It extends the BaseEntity class. Transaction has a
 * description, amount, type and user.
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

  /**
   * The description of the transaction.
   */
  private String description;
  private Double amount;

  /**
   * The type of transaction. It can be REVENUE or EXPENSE.
   */
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  /**
   * The user who made the transaction. A transaction has only one user.
   */
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
