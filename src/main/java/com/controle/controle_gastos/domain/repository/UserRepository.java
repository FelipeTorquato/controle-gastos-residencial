package com.controle.controle_gastos.domain.repository;

import com.controle.controle_gastos.domain.entity.User;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

  Optional<User> findByName(String email);
}
