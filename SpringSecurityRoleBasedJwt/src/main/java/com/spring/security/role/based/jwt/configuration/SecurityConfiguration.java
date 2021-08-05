package com.spring.security.role.based.jwt.configuration;

import com.spring.security.role.based.jwt.configuration.util.CustomAccessDeniedHandler;
import com.spring.security.role.based.jwt.configuration.util.CustomSuccessHandler;
import com.spring.security.role.based.jwt.configuration.util.CustomUserDetailsService;
import com.spring.security.role.based.jwt.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomSuccessHandler successHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        http.authorizeRequests().antMatchers("/secure/app/**").permitAll().antMatchers("/admin/**")
                .hasRole(AppConstants.ROLE_ADMIN).antMatchers("/user/**")
                .hasAnyRole(AppConstants.ROLE_ADMIN, AppConstants.ROLE_USER).antMatchers("/visitor/**")
                .hasAnyRole(AppConstants.ROLE_VISITOR, AppConstants.ROLE_ADMIN, AppConstants.ROLE_USER).anyRequest()
                .authenticated().and().formLogin().successHandler(successHandler).failureUrl("/login?access-denied")
                .and().logout().invalidateHttpSession(true).and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

}
