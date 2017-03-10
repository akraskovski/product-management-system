package by.intexsoft.security.service.impl;

import by.intexsoft.model.User;
import by.intexsoft.model.UserAuthentication;
import by.intexsoft.security.service.TokenAuthenticationService;

import by.intexsoft.security.service.TokenService;
import by.intexsoft.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("security.token.secret.key")
    private String secretKey;

    private final static String AUTH_HEADER_NAME = "x-auth-token";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            User user = getUserFromToken(tokenData);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    private User getUserFromToken(Jws<Claims> tokenData) {
        try {
            return userService.findByUsername(tokenData.getBody().get("username").toString());
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }
}
