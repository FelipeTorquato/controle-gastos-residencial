package com.controle.controle_gastos.domain.to;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the pagination parameters.
 * It is used to paginate data from the database.
 */
@Getter
@Setter
public class PaginationTO {

  private int page;

  private int size;

  public PaginationTO(int page, int size) {
    this.page = page;
    this.size = size;
  }

  private Map<String, Object> params;
}
