package by.kraskovski.pms.config;

import by.kraskovski.pms.security.filter.AuthenticationTokenFilter;
import by.kraskovski.pms.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static by.kraskovski.pms.model.enums.AuthorityEnum.*;

/**
 * Spring Security configuration class.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    @Autowired
    public SecurityConfig(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/store/**").permitAll()
                .antMatchers("/user/**").hasAuthority(ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/product/**").hasAuthority(ROLE_ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/product/**").hasAuthority(ROLE_ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/product/**").hasAuthority(ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/stock/**").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_STOCK_MANAGER.name())
                .antMatchers(HttpMethod.PUT, "/stock/**").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_STOCK_MANAGER.name())
                .antMatchers(HttpMethod.DELETE, "/stock/**").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_STOCK_MANAGER.name())
                .antMatchers(HttpMethod.POST, "/store/").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_STORE_MANAGER.name())
                .antMatchers(HttpMethod.PUT, "/store/").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_STORE_MANAGER.name())
                .antMatchers(HttpMethod.DELETE, "/store/**").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_STORE_MANAGER.name())
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
