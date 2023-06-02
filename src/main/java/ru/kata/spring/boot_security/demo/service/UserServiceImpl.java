package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public List<User> findAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getUserInfo(long id) {
        Optional<User> getUser = usersRepository.findById(id);
        return getUser.orElse(null);
    }

    @Transactional
    @Override
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    @Override
    public void update(long id, User updatedUser) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    @Transactional
    @Override
    public void delete(long id) {
        usersRepository.deleteById(id);
    }
}
