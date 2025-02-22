package com.controle.controle_gastos.infra.repository;

import com.controle.controle_gastos.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransactionRepository extends JpaRepository<Transaction, Long> {

}
