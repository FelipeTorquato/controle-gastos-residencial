package com.controle.controle_gastos.application.controller;

import com.controle.controle_gastos.domain.entity.Transaction;
import com.controle.controle_gastos.domain.exceptions.DomainException;
import com.controle.controle_gastos.domain.service.TransactionService;
import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import com.controle.controle_gastos.domain.to.TransactionTO;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for managing operations related to transactions. It handles request to
 * create and retrieve transactions.
 */
@RequestMapping(value = "/v1/transaction")
@RestController
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  /**
   * Returns a page of transactions.
   *
   * @param page number of the page.
   * @param size size of the page.
   * @return an HTTP response with 200 OK code and the page of transactions.
   */
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PageTO<Transaction>> getAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size
  ) {
    PaginationTO paginationTO = new PaginationTO(page, size);
    Map<String, Object> params = new HashMap<>();
    paginationTO.setParams(params);

    return ResponseEntity.ok(transactionService.findAll(paginationTO));
  }

  /**
   * Searches for a transaction by its id.
   *
   * @param id id from the transaction to search for.
   * @return an HTTP response with 200 OK code and the transaction found.
   * @throws DomainException if the transaction is not found.
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Transaction> getById(@Valid @PathVariable("id") Long id)
      throws DomainException {
    return ResponseEntity.ok(transactionService.findById(id));
  }

  /**
   * Creates a new transaction.
   *
   * @param transactionTO data transfer object containing the transaction's information.
   * @return an HTTP response with 201 Created code and the created transaction.
   * @throws DomainException if the user is not found or if the transaction is not allowed.
   */
  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Transaction> create(@Valid @RequestBody TransactionTO transactionTO)
      throws DomainException {
    return new ResponseEntity<>(transactionService.createTransaction(transactionTO),
        HttpStatus.CREATED);
  }
}
