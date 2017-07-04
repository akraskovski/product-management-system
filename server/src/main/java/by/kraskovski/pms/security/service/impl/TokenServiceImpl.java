package by.kraskovski.pms.security.service.impl;

import by.kraskovski.pms.domain.User;
import by.kraskovski.pms.domain.dto.TokenDTO;
import by.kraskovski.pms.security.exception.UserNotFoundException;
import by.kraskovski.pms.security.service.TokenService;
import by.kraskovski.pms.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${secret.key:JKGuhygvuh2v}")
    private String secretKey;

    @Value("${auth.header.name:x-auth-token}")
    private String authHeaderName;

    @Value("${token.expiration.time:60}")
    private int expirationTime;

    private final UserService userService;

    @Autowired
    public TokenServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public TokenDTO generate(final String username, final String password) {
        final User user = userService.findByUsername(username);
        if (ofNullable(user).isPresent() && ofNullable(password).isPresent()) {
            final Map<String, Object> tokenData = new HashMap<>();
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword()) || password.equals(user.getPassword())) {
                tokenData.put("username", user.getUsername());
                tokenData.put("password", user.getPassword());
                tokenData.put("create_date", LocalDateTime.now());
                final JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setClaims(tokenData);
                final Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE, expirationTime);
                jwtBuilder.setExpiration(calendar.getTime());
                final String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey).compact();
                return new TokenDTO(token, user);
            }
        }
        throw new BadCredentialsException("Invalid input data.");
    }

    @Override
    public Authentication authenticate(final HttpServletRequest request) throws ExpiredJwtException, SignatureException {
        final String token = request.getHeader(authHeaderName);
        if (token != null) {
            final Jws<Claims> tokenData = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            final User user = getUserFromToken(tokenData);
            if (validatePassword(tokenData, user.getPassword())) {
                user.setAuthenticated(true);
                return user;
            }
        }
        return null;
    }


    private User getUserFromToken(final Jws<Claims> tokenData) throws UsernameNotFoundException {
        final String username = tokenData.getBody().get("username").toString();
        final User user = userService.findByUsername(username);
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException("User: " + username + " not found!"));
    }

    private boolean validatePassword(final Jws<Claims> tokenData, final String userPassword) {
        final String tokenPassword = tokenData.getBody().get("password").toString();
        return tokenPassword.equals(userPassword);
    }
}
