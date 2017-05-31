package by.kraskovski.service.impl;

import by.kraskovski.model.User;
import by.kraskovski.repository.UserRepository;
import by.kraskovski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(final User object) {
        return userRepository.save(object);
    }

    @Override
    public User find(final int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(final User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(final int id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
