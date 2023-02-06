package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    public UserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> index() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User getUserId(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int i, User user) {
        User userUp = getUserId(i);
        userUp.setName(user.getName());
        userUp.setAge(user.getAge());
        userUp.setEmail(user.getEmail());
        userUp.setPassword(passwordEncoder.encode(user.getPassword()));
        userUp.setRoleSet(user.getRoleSet());
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserId(id));
    }
}
