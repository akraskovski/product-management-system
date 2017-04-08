package by.intexsoft.service.impl;

import by.intexsoft.model.Authority;
import by.intexsoft.repository.AuthorityRepository;
import by.intexsoft.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority find(int id) {
        return authorityRepository.findOne(id);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }
}
