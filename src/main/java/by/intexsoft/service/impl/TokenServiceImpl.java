package by.intexsoft.service.impl;

import by.intexsoft.model.User;
import by.intexsoft.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("security.token.secret.key")
    private String secretKey;

    @Override
    public String generate(User user, String password) {
        if (user != null) {
            Map<String, Object> tokenData = new HashMap<>();
            if (password.equals(user.getPassword())) {
                tokenData.put("username", user.getUsername());
                tokenData.put("password", user.getPassword());
                JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setClaims(tokenData);
                return jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey).compact();
            }
        }
        return null;
    }
}
