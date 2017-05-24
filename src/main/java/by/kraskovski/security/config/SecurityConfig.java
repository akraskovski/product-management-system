package by.kraskovski.security.config;

import by.kraskovski.security.filter.AuthenticationTokenFilter;
import by.kraskovski.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration class
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    @Autowired
    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/product/**").authenticated()
                .antMatchers(HttpMethod.POST,"/product/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT,"/product/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/product/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/stock/").authenticated()
                .antMatchers(HttpMethod.POST, "/stock/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_STOCK_MANAGER")
                .antMatchers(HttpMethod.PUT, "/stock/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_STOCK_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/stock/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_STOCK_MANAGER")
                .antMatchers(HttpMethod.POST, "/store/").hasAnyAuthority("ROLE_ADMIN", "ROLE_STORE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/store/").hasAnyAuthority("ROLE_ADMIN", "ROLE_STORE_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/store/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_STORE_MANAGER")
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
