package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepositoryIml;
import ru.kata.spring.boot_security.demo.security.UserDetailsWrapperImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryIml userRepositoryIml;

    public UserDetailsServiceImpl(UserRepositoryIml userRepositoryIml) {
        this.userRepositoryIml = userRepositoryIml;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryIml.findByName(username);
        if (user == null)
            throw new BadCredentialsException("Нет такого пользователя :(");
        return new UserDetailsWrapperImpl(user);
    }

}
