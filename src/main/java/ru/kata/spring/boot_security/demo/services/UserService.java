package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.Roles;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

        Set<Role> temp = new HashSet<>();
        Role role = new Role();
        role.setRole("ADMIN");
        temp.add(role);
        User admin = new User("admin", 40, "admin@mail.ru",
                "123", temp);
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        userRepository.save(admin);
        temp.clear();
        role = new Role();
        role.setRole("USER");
        temp.add(role);
        User user = new User("user", 40, "user@mail.ru",
                "123", temp);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        temp.clear();
        role = new Role();
        role.setRole("ADMIN");
        temp.add(role);
        role = new Role();
        role.setRole("USER");
        temp.add(role);
        User sup = new User("sup", 40, "sup@mail.ru",
                "123", temp);
        sup.setPassword(new BCryptPasswordEncoder().encode(sup.getPassword()));
        userRepository.save(sup);

    }

    @Transactional
    public User findUserByUserName(String username) {

        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
//        Set<? extends GrantedAuthority> grantedAuthorities = new HashSet<>();
//        user.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getAuthority())));
//        user.setRoles(grantedAuthorities);
        return user;
    }

    public Optional<User> findById(long id) {

        return userRepository.findById(id);
    }

    public void save(User user) {
        User extracted = userRepository.findByUsername(user.getUsername());
        if (extracted != null) {
            throw new IllegalArgumentException("User already exists!");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public void edit(User user) {
        User extracted = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("Edited User not exists!"));
        extracted.setUsername(user.getUsername());
        extracted.setAge(user.getAge());
        extracted.setEmail(user.getEmail());
        extracted.setRoles(user.getRoles());
        if (!passwordEncoder.matches(extracted.getPassword(), user.getPassword())) {
            extracted.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public void remove(long id) {
        userRepository.deleteById(id);
    }
}
