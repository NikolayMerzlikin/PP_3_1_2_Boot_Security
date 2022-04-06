package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface IUserService {
    void addUser(User user);
    void addUser(String name,String surname, Integer  age);
    void editUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    User getUser(long id);
}
