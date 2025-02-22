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

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

  private String description;
  private Double amount;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
