package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.repository.UserRepository;
import by.kraskovski.pms.service.AuthorityService;
import by.kraskovski.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            final UserRepository userRepository,
            final AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User create(final User object) {
        object.setCreateDate(LocalDateTime.now());
        if (object.getAuthorities().isEmpty()) {
            object.getAuthorities().add(authorityService.findByName(AuthorityEnum.ROLE_USER));
        }
        return userRepository.save(object);
    }

    @Override
    public User find(final String id) {
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
        object.setPassword(passwordEncoder.encode(object.getPassword()));
        return userRepository.save(object);
    }

    @Override
    public void delete(final String id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
