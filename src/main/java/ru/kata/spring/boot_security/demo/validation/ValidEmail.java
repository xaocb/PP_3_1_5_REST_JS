package ru.kata.spring.boot_security.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import java.lang.annotation.*;

@Email(message = "Invalid email address")
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {
    String message() default "Invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}