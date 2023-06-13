package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import ru.kata.spring.boot_security.demo.repositories.RolesRepository;

import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.Arrays;

@Component
public class DataInitializer implements InitializingBean {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DataInitializer(UsersRepository usersRepository, RolesRepository rolesRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void afterPropertiesSet(){
        Role roleAdmin = new Role(1L, "ROLE_ADMIN");
        Role roleUser = new Role(2L, "ROLE_USER");

        rolesRepository.saveAll(Arrays.asList(roleAdmin, roleUser));

        User userAdmin = new User("Admin", "Adminov", "admin@mail.com", 30);
        userAdmin.setPassword(bCryptPasswordEncoder.encode("admin"));
        userAdmin.setRoles(Arrays.asList(roleAdmin, roleUser));

        User userUser = new User("User", "Userov", "user@mail.com", 25);
        userUser.setPassword(bCryptPasswordEncoder.encode("user"));
        userUser.setRoles(Arrays.asList(roleUser));

        usersRepository.saveAll(Arrays.asList(userAdmin, userUser));
    }
}