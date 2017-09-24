package by.kraskovski.pms.service.impl;

import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.repository.UserRepository;
import by.kraskovski.pms.security.exception.UserNotFoundException;
import by.kraskovski.pms.service.AuthorityService;
import by.kraskovski.pms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public User create(final User object) {
        object.setId(null);
        object.setCreateDate(LocalDateTime.now());
        object.setPassword(PASSWORD_ENCODER.encode(object.getPassword()));
        verifyUserAuthorities(object);
        return userRepository.save(object);
    }

    @Override
    public User find(final String id) {
        return Optional.ofNullable(userRepository.findOne(id))
                .orElseThrow(() -> new UserNotFoundException("User with id: \"" + id + "\" doesn't exists in db!"));
    }

    @Override
    public User findByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("User with username: \"" + username + "\" doesn't exists in db!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(final User object) {
        final User oldUser = find(object.getId());
        if (!PASSWORD_ENCODER.matches(object.getPassword(), oldUser.getPassword())
                && !oldUser.getPassword().equals(object.getPassword())) {
            object.setPassword(PASSWORD_ENCODER.encode(object.getPassword()));
            return userRepository.save(object);
        }
        object.setPassword(oldUser.getPassword());
        verifyUserAuthorities(object);
        return userRepository.save(object);
    }

    @Override
    public void delete(final String id) {
        try {
            userRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("User with id: \"" + id + "\" doesn't exists in db!");
        }
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    private void verifyUserAuthorities(final User object) {
        if (object == null) {
            throw new IllegalArgumentException("Can't verify user authorities, because user is null!");
        }
        if (CollectionUtils.isEmpty(object.getAuthorities())) {
            object.setAuthorities(singletonList(authorityService.findByName(AuthorityEnum.ROLE_USER)));
        } else {
            object.setAuthorities(object.getAuthorities().stream()
                    .map(authority -> authorityService.find(authority.getId()))
                    .collect(toList()));
        }
    }
}
