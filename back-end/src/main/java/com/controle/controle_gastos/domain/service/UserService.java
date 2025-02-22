package com.controle.controle_gastos.domain.service;

import com.controle.controle_gastos.domain.entity.Transaction;
import com.controle.controle_gastos.domain.entity.TransactionType;
import com.controle.controle_gastos.domain.entity.User;
import com.controle.controle_gastos.domain.entity.UserRole;
import com.controle.controle_gastos.domain.exceptions.DomainException;
import com.controle.controle_gastos.domain.exceptions.ErrorCode;
import com.controle.controle_gastos.domain.repository.UserRepository;
import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import com.controle.controle_gastos.domain.to.SummaryResponseTO;
import com.controle.controle_gastos.domain.to.UserSummaryTO;
import com.controle.controle_gastos.domain.to.UserTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service responsible for managing operations related to users. Includes methods to create,
 * retrieve, delete users and get a summary of all users.
 */
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Returns a page of users.
   *
   * @param paginationTO pagination parameters.
   * @return a page of users.
   */
  public PageTO<User> findAll(PaginationTO paginationTO) {
    return userRepository.findAll(paginationTO);
  }

  /**
   * Searches for a user by its id.
   *
   * @param id id from the user to search for.
   * @return the user found.
   * @throws DomainException if the user is not found.
   */
  public User findById(Long id) throws DomainException {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new DomainException("User not found", ErrorCode.USER_NOT_FOUND);
    }
    return user.get();
  }

  /**
   * Creates a new user. It checks if the name is already taken. If not, it creates the user giving
   * a role based on the age.
   *
   * @param userTO data transfer object containing the user's information.
   * @return the created user.
   * @throws DomainException if the user already exists.
   */
  public User createUser(UserTO userTO) throws DomainException {

    Optional<User> userDB = userRepository.findByName(userTO.getName().trim());

    if (userDB.isPresent()) {
      throw new DomainException("The name " + userTO.getName() + " already exists.",
          ErrorCode.NAME_ALREADY_EXISTS);
    }

    User user = User.builder()
        .name(userTO.getName().trim())
        .age(userTO.getAge())
        .role(userTO.getAge() >= 18 ? UserRole.ADULT : UserRole.MINOR)
        .build();

    return userRepository.save(user);
  }

  /**
   * Deletes a user by its id.
   *
   * @param id id from the user to delete.
   * @throws DomainException if the user is not found.
   */
  public void deleteUser(Long id) throws DomainException {
    Optional<User> userDB = userRepository.findById(id);
    if (userDB.isEmpty()) {
      throw new DomainException("User not found", ErrorCode.USER_NOT_FOUND);
    }
    userRepository.delete(userDB.get());
  }

  /**
   * Returns a summary of all users. This method calculates the total revenue, expense and net
   * balance for each user and returns a list of UserSummaryTO objects. After that, it calculates
   * the total revenue, expense and net balance from all users.
   *
   * @return a summary of all users.
   */
  public SummaryResponseTO getSummary() {
    List<User> users = userRepository.findAll();
    List<UserSummaryTO> userSummaries = new ArrayList<>();
    double totalRevenue = 0.0;
    double totalExpense = 0.0;
    double totalNetRevenue = 0.0;

    // For each user, calculate the total revenue, expense and net balance
    for (User user : users) {
      double userRevenue = 0.0;
      double userExpense = 0.0;
      double userNetBalance;

      // For each transaction, get the amount of the transaction and add it to the corresponding total
      for (Transaction transaction : user.getTransactions()) {
        if (transaction.getType() == TransactionType.REVENUE) {
          userRevenue += transaction.getAmount();
        } else if (transaction.getType() == TransactionType.EXPENSE) {
          userExpense += transaction.getAmount();
        }
      }

      // Calculate the net balance subtracting the expense from the revenue
      userNetBalance = userRevenue - userExpense;

      // Add the user to the list of summaries
      userSummaries.add(new UserSummaryTO(
          user.getId(),
          user.getName(),
          user.getAge(),
          userRevenue,
          userExpense,
          userNetBalance
      ));

      // Add the revenue and expense to the totals. The net balance is calculated from the totals.
      totalRevenue += userRevenue;
      totalExpense += userExpense;
      totalNetRevenue = totalRevenue - totalExpense;

    }

    // Return a single summary with all the information
    return new SummaryResponseTO(userSummaries, totalRevenue, totalExpense, totalNetRevenue);
  }
}
