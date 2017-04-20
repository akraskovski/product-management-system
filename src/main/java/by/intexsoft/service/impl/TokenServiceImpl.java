package by.intexsoft.service.impl;

import by.intexsoft.model.User;
import by.intexsoft.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
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
            if (password.equals(user.password)) {
                tokenData.put("username", user.username);
                tokenData.put("password", user.password);
                tokenData.put("token_create_date", LocalDateTime.now());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE, 60);
                tokenData.put("token_expiration_date", calendar.getTime());
                JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setExpiration(calendar.getTime());
                jwtBuilder.setClaims(tokenData);
                return jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey).compact();
            }
        }
        return null;
    }
}
