package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.validation.ValidEmail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import java.util.Collection;
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
//    @Size(min = 2, max = 15, message = "Name should be between 2 and 15 characters")
//    @NotEmpty(message = "This field can not be empty")
    private String firstName;

    @Column(name = "last_name")
//    @NotEmpty(message = "This field can not be empty")
//    @Size(min = 2, max = 15, message = "Surname should be between 2 and 15 characters")
    private String lastName;

    @Column(name = "age")
//    @Min(1)
//    @Max(150)
    private Integer age;
//    @ValidEmail
    @Column(name = "email")
//    @NotEmpty(message = "This field can not be empty")
//    @ValidEmail(message = "Enter correct email-adress")
    private String email;
    @Column
//    @NotEmpty(message = "This field can not be empty")
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
