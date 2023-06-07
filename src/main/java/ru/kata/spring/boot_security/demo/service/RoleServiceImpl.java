package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{

    private final RolesRepository rolesRepository;

    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Role> listRoles() {
        return rolesRepository.findAll();
    }
}
