package by.kraskovski.security.service.impl;

import by.kraskovski.model.User;
import by.kraskovski.security.service.TokenService;
import by.kraskovski.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("security.token.secret.key")
    private String secretKey;
    private final String AUTH_HEADER_NAME = "x-auth-token";
    private final UserService userService;

    @Autowired
    public TokenServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String generate(User user, String password) {
        if (user != null) {
            Map<String, Object> tokenData = new HashMap<>();
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword()) || password.equals(user.getPassword())) {
                tokenData.put("username", user.getUsername());
                tokenData.put("password", user.getPassword());
                JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setClaims(tokenData);
                return jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey).compact();
            }
        }
        return null;
    }

    @Override
    public Authentication authenticate(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            User user = getUserFromToken(tokenData);
            if (validatePassword(tokenData, user))
                return user;
        }
        return null;
    }

    private User getUserFromToken(Jws<Claims> tokenData) throws UsernameNotFoundException {
        return userService.findByUsername(tokenData.getBody().get("username").toString());
    }

    private boolean validatePassword(Jws<Claims> tokenData, User user) {
        return user != null && tokenData.getBody().get("password").toString().equals(user.getPassword());
    }
}
