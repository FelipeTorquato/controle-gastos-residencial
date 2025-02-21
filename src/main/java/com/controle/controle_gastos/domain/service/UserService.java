package com.controle.controle_gastos.domain.service;

import com.controle.controle_gastos.domain.entity.User;
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

  public PageTO<User> getAll(PaginationTO paginationTO) {
    return userRepository.findAll(paginationTO);
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
        .build();

    return userRepository.save(user);
  }
}
