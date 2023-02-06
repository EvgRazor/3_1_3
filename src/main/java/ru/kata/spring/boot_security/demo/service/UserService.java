package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> index();
    User getUserId (int id);
    void saveUser (User user);
    void updateUser(int i, User user);
    void deleteUser(int id);

}