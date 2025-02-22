package com.controle.controle_gastos.application.controller;

import com.controle.controle_gastos.domain.entity.User;
import com.controle.controle_gastos.domain.exceptions.DomainException;
import com.controle.controle_gastos.domain.service.UserService;
import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import com.controle.controle_gastos.domain.to.SummaryResponseTO;
import com.controle.controle_gastos.domain.to.UserTO;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for managing operations related to users. It handles requests to create,
 * retrieve and delete users.
 */
@RequestMapping(value = "/v1/user")
@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Returns a page of users.
   *
   * @param page number of the page.
   * @param size size of the page.
   * @return an HTTP response with 200 OK code and the page of users.
   */
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PageTO<User>> getAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size
  ) {
    PaginationTO paginationTO = new PaginationTO(page, size);
    Map<String, Object> params = new HashMap<>();
    paginationTO.setParams(params);

    return ResponseEntity.ok(userService.findAll(paginationTO));
  }

  /**
   * Searches for a user by its id.
   *
   * @param id id from the user to search for.
   * @return an HTTP response with 200 OK code and the user found.
   * @throws DomainException if the user is not found.
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getById(@Valid @PathVariable("id") long id) throws DomainException {
    return ResponseEntity.ok(userService.findById(id));
  }

  /**
   * Creates a new user.
   *
   * @param userTO data transfer object containing the user's information.
   * @return an HTTP response with 201 Created code and the created user.
   * @throws DomainException if the user already exists.
   */
  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> create(@Valid @RequestBody UserTO userTO) throws DomainException {
    return new ResponseEntity<>(userService.createUser(userTO), HttpStatus.CREATED);
  }

  /**
   * Deletes a user by its id.
   *
   * @param id from of the user to delete.
   * @return an HTTP response with 204 No Content indicating that the user was deleted.
   * @throws DomainException if the user is not found.
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> delete(@PathVariable("id") long id) throws DomainException {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Returns a summary of all users.
   *
   * @return an HTTP response with 200 OK code and the summary of all users.
   */
  @GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SummaryResponseTO> getSummary() {
    return ResponseEntity.ok(userService.getSummary());
  }
}
