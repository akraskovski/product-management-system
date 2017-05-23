package by.kraskovski.service.impl;

import by.kraskovski.model.User;
import by.kraskovski.security.exception.UserNotFoundException;
import by.kraskovski.service.TokenAuthenticationService;
import by.kraskovski.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("secret.key")
    private String secretKey;

    private final static String AUTH_HEADER_NAME = "x-auth-token";

    private final UserService userService;

    @Autowired
    public TokenAuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            User user = getUserFromToken(tokenData);
            if (validatePassword(tokenData, user)) {
                return user;
            }
        }
        return null;
    }

    private User getUserFromToken(Jws<Claims> tokenData) {
        try {
            return userService.findByUsername(tokenData.getBody().get("username").toString());
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException("User:" + tokenData.getBody().get("username").toString() + " not found");
        }
    }

    private boolean validatePassword(Jws<Claims> tokenData, User user) {
        return user != null && tokenData.getBody().get("password").toString().equals(user.getPassword());
    }
}
