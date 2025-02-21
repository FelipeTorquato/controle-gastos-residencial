package com.controle.controle_gastos.infra.repository;

import com.controle.controle_gastos.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, Long> {

}
