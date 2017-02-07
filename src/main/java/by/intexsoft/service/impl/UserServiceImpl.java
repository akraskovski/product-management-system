package by.intexsoft.service.impl;

import by.intexsoft.model.User;
import by.intexsoft.repository.UserRepository;
import by.intexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService} class.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUser(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}