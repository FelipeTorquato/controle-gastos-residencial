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

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public PageTO<User> findAll(PaginationTO paginationTO) {
    return userRepository.findAll(paginationTO);
  }

  public User findById(Long id) throws DomainException {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new DomainException("User not found", ErrorCode.USER_NOT_FOUND);
    }
    return user.get();
  }

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

  public void deleteUser(Long id) throws DomainException {
    Optional<User> userDB = userRepository.findById(id);
    if (userDB.isEmpty()) {
      throw new DomainException("User not found", ErrorCode.USER_NOT_FOUND);
    }
    userRepository.delete(userDB.get());
  }

  public SummaryResponseTO getSummary() throws DomainException {
    List<User> users = userRepository.findAll();
    List<UserSummaryTO> userSummaries = new ArrayList<>();
    double totalRevenue = 0.0;
    double totalExpense = 0.0;
    double totalNetRevenue = 0.0;

    for (User user : users) {
      double userRevenue = 0.0;
      double userExpense = 0.0;
      double userNetBalance = 0.0;

      for (Transaction transaction : user.getTransactions()) {
        if (transaction.getType() == TransactionType.REVENUE) {
          userRevenue += transaction.getAmount();
        } else if (transaction.getType() == TransactionType.EXPENSE) {
          userExpense += transaction.getAmount();
        }
      }

      userNetBalance = userRevenue - userExpense;

      userSummaries.add(new UserSummaryTO(
          user.getId(),
          user.getName(),
          user.getAge(),
          userRevenue,
          userExpense,
          userNetBalance
      ));

      totalRevenue += userRevenue;
      totalExpense += userExpense;
      totalNetRevenue = totalRevenue - totalExpense;

    }

    return new SummaryResponseTO(userSummaries, totalRevenue, totalExpense, totalNetRevenue);
  }
}
