package com.controle.controle_gastos.domain.repository;

import com.controle.controle_gastos.domain.entity.Transaction;

public interface TransactionRepository extends BaseRepository<Transaction, Long> {

  void delete(Transaction transaction);
}
