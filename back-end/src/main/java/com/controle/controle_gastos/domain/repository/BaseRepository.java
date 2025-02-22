package com.controle.controle_gastos.domain.repository;

import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import java.util.List;
import java.util.Optional;

/**
 * Base interface for all repositories. It provides CRUD operations for entities.
 *
 * @param <T>  entity type
 * @param <ID> entity id type
 */
public interface BaseRepository<T, ID> {

  Optional<T> findById(ID id);

  PageTO<T> findAll(PaginationTO paginationTO);

  List<T> findAll();

  T save(T entity);
}
