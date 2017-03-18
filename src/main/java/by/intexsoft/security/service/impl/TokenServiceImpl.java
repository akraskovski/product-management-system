package by.intexsoft.security.service.impl;

import by.intexsoft.model.User;
import by.intexsoft.security.service.TokenService;
import by.intexsoft.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("security.token.secret.key")
    private String secretKey;

    private final UserService userService;

    @Autowired
    public TokenServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String generate(String username, String password) {
        if (isEmpty(username) || isEmpty(password))
            return null;
        User user = userService.findByUsername(username);
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
