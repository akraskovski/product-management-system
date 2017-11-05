package by.kraskovski.pms.application.security.filter;

import by.kraskovski.pms.application.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Main validation filter
 */
@AllArgsConstructor
public class AuthenticationTokenFilter extends GenericFilterBean {

    private final TokenService tokenService;

    /**
     * Validate income user with token
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final Authentication authentication = tokenService.authenticate(httpRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
