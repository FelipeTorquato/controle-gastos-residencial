package com.controle.controle_gastos.domain.to;

import com.controle.controle_gastos.domain.entity.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionTO {

  @NotNull
  @NotBlank
  private String description;

  @NotNull
  @Min(value = 0, message = "Amount must be at least 0")
  private Double amount;

  @NotNull
  private TransactionType type;

  private Integer userId;
}
