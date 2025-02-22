package com.controle.controle_gastos.domain.service;

import com.controle.controle_gastos.domain.entity.User;
import com.controle.controle_gastos.domain.entity.UserRole;
import com.controle.controle_gastos.domain.exceptions.DomainException;
import com.controle.controle_gastos.domain.exceptions.ErrorCode;
import com.controle.controle_gastos.domain.repository.UserRepository;
import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import com.controle.controle_gastos.domain.to.UserTO;
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

    Optional<User> userDB = userRepository.findByName(userTO.getName());

    if (userDB.isPresent()) {
      throw new DomainException("The name " + userTO.getName() + " already exists.",
          ErrorCode.NAME_ALREADY_EXISTS);
    }

    User user = User.builder()
        .name(userTO.getName())
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
    //delete all transactions from user
    userRepository.delete(userDB.get());
  }
}
