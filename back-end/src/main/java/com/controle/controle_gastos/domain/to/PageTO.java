package com.controle.controle_gastos.domain.to;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a page of data.
 * It is used to return data from the database.
 * @param <T>
 */
@Getter
@Setter
public class PageTO<T> {

  private List<T> content;

  private long total;

  private int page;

  private int size;

  public PageTO(List<T> content, long total) {
    this.content = content;
    this.total = total;
  }

  public PageTO(List<T> content, long total, int page, int size) {
    this.content = content;
    this.total = total;
    this.page = page;
    this.size = size;
  }

  public static <T> PageTO<T> of(PageTO<?> other, List<T> newContent) {
    return new PageTO<>(newContent, other.total, other.page, other.size);
  }
}
