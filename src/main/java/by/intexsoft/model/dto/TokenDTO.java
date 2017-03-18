package by.intexsoft.model.dto;

import by.intexsoft.model.User;

import java.io.Serializable;

public class TokenDTO implements Serializable {

    public String token;

    public User user;

    public TokenDTO(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
