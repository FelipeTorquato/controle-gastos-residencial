package com.controle.controle_gastos.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all entities. At the moment, it only provides the id field.
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class BaseEntity {

  /**
   * The id of the entity.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
