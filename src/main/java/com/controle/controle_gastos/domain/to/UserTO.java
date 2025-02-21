package com.controle.controle_gastos.domain.to;

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
public class UserTO {

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @Min(value = 0, message = "Age must be at least 0")
  private Integer age;
}
