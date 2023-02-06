package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> index();
    User getUserId (int id);
    void saveUser (User user);
    void updateUser(int i, User user);
    void deleteUser(int id);
}
