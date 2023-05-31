package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUsers();

    User getUserInfo(long id);

    void save(User user);

    void update(long id, User updatedUser);

    void delete(long id);

    User findByUsername(String name);
}
