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

/**
 * User Data Transfer Object.
 * It is used to transfer data from the client to the server or vice versa.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserTO {

  /**
   * Name can not be null or empty, and must be at least 2 characters long.
   */
  @NotNull
  @NotEmpty
  @Size(min = 2, message = "{validation.name.size.too_short}")
  private String name;

  /**
   * Age can not be null, and must be at least 0.
   */
  @NotNull
  @Min(value = 0, message = "Age must be at least 0")
  private Integer age;
}
