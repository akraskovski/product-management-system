package by.intexsoft.service;

import by.intexsoft.model.Authority;

import java.util.List;

public interface AuthorityService {

    Authority find(Integer id);

    List<Authority> findAll();
}
