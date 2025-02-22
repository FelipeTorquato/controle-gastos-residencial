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

@RequestMapping(value = "/v1/transaction")
@RestController
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

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

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Transaction> getById(@Valid @PathVariable("id") Long id) throws DomainException {
    return ResponseEntity.ok(transactionService.findById(id));
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Transaction> create(@Valid @RequestBody TransactionTO transactionTO)
      throws DomainException {
    return new ResponseEntity<>(transactionService.createTransaction(transactionTO),
        HttpStatus.CREATED);
  }
}
