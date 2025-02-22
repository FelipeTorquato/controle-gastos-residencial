package com.controle.controle_gastos.domain.to;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
