package com.controle.controle_gastos.domain.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User Summary used to display the summary from a user and his revenue, expense and net balance.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSummaryTO {

  private Long userId;
  private String userName;
  private Integer userAge;
  private Double totalUserRevenue;
  private Double totalUserExpense;
  private Double userNetBalance;
}
