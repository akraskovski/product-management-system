package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.repository.UserRepository;
import by.kraskovski.pms.security.exception.UserNotFoundException;
import by.kraskovski.pms.service.AuthorityService;
import by.kraskovski.pms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public User create(final User object) {
        object.setId(null);
        object.setCreateDate(LocalDateTime.now());
        object.setPassword(PASSWORD_ENCODER.encode(object.getPassword()));
        if (object.getAuthorities().isEmpty()) {
            object.getAuthorities().add(authorityService.findByName(AuthorityEnum.ROLE_USER));
        }
        return userRepository.save(object);
    }

    @Override
    public User find(final String id) {
        return Optional.ofNullable(userRepository.findOne(id))
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " doesn't not exists in db!"));
    }

    @Override
    public User findByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " doesn't not exists in db!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(final User object) {
        final User oldUser = userRepository.findOne(object.getId());
        if (!PASSWORD_ENCODER.matches(object.getPassword(), oldUser.getPassword())
                && !oldUser.getPassword().equals(object.getPassword())) {
            object.setPassword(PASSWORD_ENCODER.encode(object.getPassword()));
            return userRepository.save(object);
        }
        object.setPassword(oldUser.getPassword());
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
