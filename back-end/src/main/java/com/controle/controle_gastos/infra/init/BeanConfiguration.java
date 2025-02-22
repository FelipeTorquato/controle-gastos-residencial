package com.controle.controle_gastos.infra.init;

import com.controle.controle_gastos.domain.repository.TransactionRepository;
import com.controle.controle_gastos.domain.repository.UserRepository;
import com.controle.controle_gastos.domain.service.TransactionService;
import com.controle.controle_gastos.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class responsible for configuring the beans of the application.
 */
@Configuration
public class BeanConfiguration {

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserService(userRepository);
  }

  @Bean
  public TransactionService transactionService(TransactionRepository transactionRepository,
      UserService userService) {
    return new TransactionService(transactionRepository, userService);
  }
}
