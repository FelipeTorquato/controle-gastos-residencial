package com.controle.controle_gastos.domain.service;

import com.controle.controle_gastos.domain.entity.Transaction;
import com.controle.controle_gastos.domain.entity.TransactionType;
import com.controle.controle_gastos.domain.entity.User;
import com.controle.controle_gastos.domain.entity.UserRole;
import com.controle.controle_gastos.domain.exceptions.DomainException;
import com.controle.controle_gastos.domain.exceptions.ErrorCode;
import com.controle.controle_gastos.domain.repository.TransactionRepository;
import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import com.controle.controle_gastos.domain.to.TransactionTO;
import java.util.Optional;

/**
 * Service responsible for managing operations related to transactions. Includes methods to create
 * and retrieve transactions.
 */
public class TransactionService {

  private final TransactionRepository transactionRepository;

  private final UserService userService;

  public TransactionService(TransactionRepository transactionRepository, UserService userService) {
    this.transactionRepository = transactionRepository;
    this.userService = userService;
  }

  /**
   * Returns a page of transactions.
   *
   * @param paginationTO pagination parameters.
   * @return a page of transactions.
   */
  public PageTO<Transaction> findAll(PaginationTO paginationTO) {
    return transactionRepository.findAll(paginationTO);
  }

  /**
   * @param id id from the transaction to search for.
   * @return the transaction found.
   * @throws DomainException if the transaction is not found.
   */
  public Transaction findById(Long id) throws DomainException {
    Optional<Transaction> transaction = transactionRepository.findById(id);
    if (transaction.isEmpty()) {
      throw new DomainException("Transaction not found", ErrorCode.TRANSACTION_NOT_FOUND);
    }
    return transaction.get();
  }

  /**
   * Creates a new transaction. It checks if the user exists. If yes, it checks if the user can
   * create a REVENUE transaction.
   *
   * @param transactionTO data transfer object containing the transaction's information.
   * @return the created transaction.
   * @throws DomainException if the user is not found or if the transaction type is not allowed.
   */
  public Transaction createTransaction(TransactionTO transactionTO) throws DomainException {
    User user = userService.findById(transactionTO.getUserId());

    verifyIfUserCanRevenue(user.getRole().toString(), transactionTO.getType().toString());

    Transaction transaction = Transaction.builder()
        .description(transactionTO.getDescription().trim())
        .amount(transactionTO.getAmount())
        .type(transactionTO.getType())
        .user(user)
        .build();
    return transactionRepository.save(transaction);
  }

  /**
   * Verifies if the user can create a REVENUE transaction.
   *
   * @param userRole        role of the user.
   * @param transactionType type of the transaction.
   * @throws DomainException if the user can't create a REVENUE transaction.
   */
  private void verifyIfUserCanRevenue(String userRole, String transactionType)
      throws DomainException {
    if (userRole.equalsIgnoreCase(UserRole.MINOR.toString()) && transactionType.equalsIgnoreCase(
        TransactionType.REVENUE.toString())) {
      throw new DomainException("You can't create a transaction of type REVENUE for a minor",
          ErrorCode.MINOR_CANT_CREATE_REVENUE);
    }
  }
}
