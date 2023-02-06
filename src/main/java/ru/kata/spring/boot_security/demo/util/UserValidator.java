package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepositoryIml;

@Component
public class UserValidator implements Validator {

    private final UserRepositoryIml userRepositoryIml;

    public UserValidator(UserRepositoryIml userRepositoryIml) {
        this.userRepositoryIml = userRepositoryIml;
    }

    // к какому классу он относиться
    @Override
    public boolean supports(Class<?> classe) {
        return User.class .equals(classe);
    }

    // сам метод для валидации. Вызываем его потмо через контроллер. Вызываем на объете,
    // который приходит с формы
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        User usersName = userRepositoryIml.findByName(user.getName());
        User userEmail = userRepositoryIml.findByEmail(user.getEmail());

        if (usersName != null) {
            errors.rejectValue("name", "", "Имя занято, выберите другое");
        }
        if (userEmail != null) {
            errors.rejectValue("email", "", "Email занят, выберите другой");
        }

    }
}
