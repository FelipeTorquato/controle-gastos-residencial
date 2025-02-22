package com.controle.controle_gastos.domain.repository;

import com.controle.controle_gastos.domain.entity.User;
import java.util.Optional;

/**
 * Interface responsible for managing operations related to users.
 */
public interface UserRepository extends BaseRepository<User, Long> {

  Optional<User> findByName(String name);

  void delete(User user);
}
