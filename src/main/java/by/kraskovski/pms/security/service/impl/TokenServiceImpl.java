package by.kraskovski.pms.security.service.impl;

import by.kraskovski.pms.model.User;
import by.kraskovski.pms.model.dto.TokenDTO;
import by.kraskovski.pms.security.service.TokenService;
import by.kraskovski.pms.service.UserService;
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

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${secret.key:JKGuhygvuh2v}")
    private String secretKey;
    @Value("${auth.header.name:x-auth-token}")
    private String authHeaderName;
    private final UserService userService;

    @Autowired
    public TokenServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public TokenDTO generate(final String username, final String password) {
        final User user = userService.findByUsername(username);
        if (user != null) {
            final Map<String, Object> tokenData = new HashMap<>();
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword()) || password.equals(user.getPassword())) {
                tokenData.put("username", user.getUsername());
                tokenData.put("password", user.getPassword());
                final JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setClaims(tokenData);
                final String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey).compact();
                return createTokenDTO(user, token);
            }
        }
        return null;
    }

    private TokenDTO createTokenDTO(final User user, String token) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUser(user);
        tokenDTO.getUser().setPassword(EMPTY);
        tokenDTO.setToken(token);
        return tokenDTO;
    }

    @Override
    public Authentication authenticate(final HttpServletRequest request) {
        final String token = request.getHeader(authHeaderName);
        if (token != null) {
            final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            final User user = getUserFromToken(tokenData);
            if (validatePassword(tokenData, user)) {
                return user;
            }
        }
        return null;
    }

    private User getUserFromToken(final Jws<Claims> tokenData) throws UsernameNotFoundException {
        return userService.findByUsername(tokenData.getBody().get("username").toString());
    }

    private boolean validatePassword(final Jws<Claims> tokenData, final User user) {
        return user != null && tokenData.getBody().get("password").toString().equals(user.getPassword());
    }
}
