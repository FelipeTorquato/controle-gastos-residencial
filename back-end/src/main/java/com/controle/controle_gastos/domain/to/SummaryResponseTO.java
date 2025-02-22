package com.controle.controle_gastos.domain.to;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Summary Response used to display the summary of all users as a list and their data.
 * After that, it calculates the total revenue, expense and net balance from the family.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SummaryResponseTO {

  private List<UserSummaryTO> userSummaries;
  private Double totalRevenue;
  private Double totalExpense;
  private Double totalNetBalance;

}
