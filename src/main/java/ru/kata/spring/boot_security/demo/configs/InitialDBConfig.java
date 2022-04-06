package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.Roles;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.Set;

//@Configuration
//public class InitialDBConfig {
//
//    @Bean
//    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
//        return args -> {
//            Set<Role> trmp = new HashSet<>();
//            trmp.add(new Role("ADMIN"));
//            User user = new User("a", 40, "admiiin@mail.ru",
//                    "123", trmp);
//            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//            userRepository.save(user);
//            userRepository.save(user);
//        };
//    }
//}
