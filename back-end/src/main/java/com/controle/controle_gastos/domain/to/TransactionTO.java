package com.controle.controle_gastos.domain.to;

import com.controle.controle_gastos.domain.entity.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transaction Data Transfer Object.
 * It is used to transfer data from the client to the server or vice versa.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionTO {

  /**
   * Represents the description of the transaction.
   * Can not be null or empty, and must be at most 256 characters long.
   */
  @NotNull
  @NotBlank
  @Size(max = 256, message = "{validation.name.size.too_long}")
  private String description;

  /**
   * Represents the amount of the transaction.
   * Can not be null, and must be at least 0.
   */
  @NotNull
  @Min(value = 0, message = "Amount must be at least 0")
  private Double amount;

  /**
   * Represents the type of the transaction, which can be REVENUE or EXPENSE.
   */
  @NotNull
  private TransactionType type;

  /**
   * The id of the user who made the transaction.
   * This user must exist in the database.
   */
  private Long userId;
}
