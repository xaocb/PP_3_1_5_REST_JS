package ru.kata.spring.boot_security.demo.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.Objects;

@Component
public class EmailValidator implements Validator {

    private final UsersRepository usersRepository;

    public EmailValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

    public void validateEditedUserEmail(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        User user = (User) target;
        User currentUser = usersRepository.findById(user.getId()).get();
        String email = user.getEmail();
        if (!isValid(email) & !Objects.equals(currentUser.getEmail(), user.getEmail())) {
            errors.rejectValue("email", "InvalidEmail");
        }
    }

    public void validateNewUserEmail(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        User user = (User) target;
        String email = user.getEmail();
        if (!isValid(email)) {
            errors.rejectValue("email", "InvalidEmail");
        }
    }

    private boolean isValid(String email) {
        return usersRepository.countByEmail(email) == 0;
    }
}