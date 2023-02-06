package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepositoryIml;

import java.security.Principal;

@Controller
public class UserController {

    private final UserRepositoryIml userRepositoryIml;

    public UserController(UserRepositoryIml userRepositoryIml) {
        this.userRepositoryIml = userRepositoryIml;
    }

    @GetMapping("/user")
    public String pageUser (Model model, Principal principal) {
        User user = userRepositoryIml.findByName(principal.getName());
        model.addAttribute("_user", user);
        System.out.println(principal.getName());
        return "/user";
    }

}
