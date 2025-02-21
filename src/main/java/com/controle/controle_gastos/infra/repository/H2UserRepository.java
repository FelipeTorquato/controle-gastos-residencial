package com.controle.controle_gastos.infra.repository;

import com.controle.controle_gastos.domain.entity.User;
import com.controle.controle_gastos.domain.repository.UserRepository;
import com.controle.controle_gastos.domain.to.PageTO;
import com.controle.controle_gastos.domain.to.PaginationTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class H2UserRepository implements UserRepository {

  private final SpringDataUserRepository userRepository;

  public H2UserRepository(SpringDataUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public PageTO<User> findAll(PaginationTO paginationTO) {
    Pageable pageable = PageRequest.of(paginationTO.getPage(), paginationTO.getSize());
    Page<User> users = userRepository.findAll(pageable);
    return new PageTO<>(users.getContent(), users.getTotalElements(), users.getNumber(),
        users.getSize());
  }

  @Override
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> findByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public User save(User entity) {
    return userRepository.save(entity);
  }
}