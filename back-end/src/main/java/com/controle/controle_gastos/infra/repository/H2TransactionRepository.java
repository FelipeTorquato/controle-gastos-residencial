package com.controle.controle_gastos.infra.repository;

import com.controle.controle_gastos.domain.entity.Transaction;
import com.controle.controle_gastos.domain.repository.TransactionRepository;
import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class H2TransactionRepository implements TransactionRepository {

  private final SpringDataTransactionRepository transactionRepository;

  public H2TransactionRepository(SpringDataTransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public PageTO<Transaction> findAll(PaginationTO paginationTO) {
    Pageable pageable = PageRequest.of(paginationTO.getPage(), paginationTO.getSize());
    Page<Transaction> transactions = transactionRepository.findAll(pageable);
    return new PageTO<>(transactions.getContent(), transactions.getTotalElements(),
        transactions.getNumber(),
        transactions.getSize());
  }

  @Override
  public List<Transaction> findAll() {
    return transactionRepository.findAll();
  }

  @Override
  public Optional<Transaction> findById(Long id) {
    return transactionRepository.findById(id);
  }

  @Override
  public Transaction save(Transaction entity) {
    return transactionRepository.save(entity);
  }

  @Override
  public void delete(Transaction transaction) {
    transactionRepository.delete(transaction);
  }
}
