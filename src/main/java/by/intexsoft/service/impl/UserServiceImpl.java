package by.intexsoft.service.impl;

import by.intexsoft.model.User;
import by.intexsoft.repository.UserRepository;
import by.intexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService} service
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User object) {
        return userRepository.save(object);
    }

    @Override
    public User find(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Integer id, User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}