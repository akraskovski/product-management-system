package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthorityService implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority create(final Authority object) {
        return authorityRepository.save(object);
    }

    @Override
    public Authority find(final String id) {
        return ofNullable(authorityRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException(format("Authority with id: \"%s\" doesn't exists in db!", id)));
    }

    @Override
    public Authority findByName(final AuthorityEnum name) {
        return ofNullable(authorityRepository.findByName(name))
                .orElseThrow(() -> new EntityNotFoundException(format("Authority: \"%s\" doesn't exists in db!", name)));
    }

    @Override
    public Authority update(final Authority object) {
        return authorityRepository.save(object);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public void delete(final String id) {
        authorityRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        authorityRepository.deleteAll();
    }
}
