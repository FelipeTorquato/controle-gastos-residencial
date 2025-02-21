package com.controle.controle_gastos.infra.init;

import com.controle.controle_gastos.domain.repository.UserRepository;
import com.controle.controle_gastos.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserService(userRepository);
  }
}
