package com.controle.controle_gastos.domain.to;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserTO {

  @NotNull
  @NotEmpty
  @Size(min = 2, message = "{validation.name.size.too_short}")
  private String name;

  @NotNull
  @Min(value = 0, message = "Age must be at least 0")
  private Integer age;
}
