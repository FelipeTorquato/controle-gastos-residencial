package com.controle.controle_gastos.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user in the system. It extends the BaseEntity class. User has a name, age, role and
 * transactions.
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

  /**
   * The name of the user. This name must be unique.
   */
  @Column(unique = true)
  private String name;

  private Integer age;

  /**
   * The role of the user. It can be ADULT or MINOR.
   */
  private UserRole role;

  /**
   * List of transactions made by the user. A user can have multiple transactions. It has the
   * @JsonIgnore annotation to ignore it when serializing to JSON.
   */
  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Transaction> transactions;
}
