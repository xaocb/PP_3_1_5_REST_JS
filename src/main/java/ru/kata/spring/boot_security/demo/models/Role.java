package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Role() {
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    public String getNoPrefix() {
        String pr = "ROLE_";
        return name.substring(pr.length());
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if ("ROLE_USER".equals(getAuthority())) {
            return "USER";
        } else if ("ROLE_ADMIN".equals(getAuthority())) {
            return "ADMIN";
        } else {
            return getAuthority();
        }
    }
}
