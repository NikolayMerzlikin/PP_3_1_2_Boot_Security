package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {

    private UserRepository repoSpringData;

    @Autowired
    public void setRepoSpringData(UserRepository repoSpringData) {
        this.repoSpringData = repoSpringData;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addUser(String name, String surname, Integer age) {

    }

    @Override
    public void editUser(User user) {

    }



    @Override
    public void deleteUser(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Incorrect value of Id");
        }
        repoSpringData.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(repoSpringData.findAll());

    }

    @Override
    public User getUser(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Incorrect value of Id");
        }
        return repoSpringData.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("This user is not exist");
        });
    }
}
