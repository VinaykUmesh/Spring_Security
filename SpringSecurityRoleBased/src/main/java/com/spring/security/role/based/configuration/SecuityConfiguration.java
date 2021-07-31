package com.spring.security.role.based.configuration;

import com.spring.security.role.based.configuration.service.CustomAccessDeniedHandler;
import com.spring.security.role.based.configuration.service.CustomSuccessHandler;
import com.spring.security.role.based.configuration.service.CustomUserDetailsService;
import com.spring.security.role.based.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecuityConfiguration extends WebSecurityConfigurerAdapter {

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
        http.authorizeRequests().antMatchers("/secure/**","/login").permitAll().antMatchers("/admin/**")
                .hasRole(AppConstants.ROLE_ADMIN).antMatchers("/user/**")
                .hasAnyRole(AppConstants.ROLE_ADMIN, AppConstants.ROLE_USER).antMatchers("/visitor")
                .hasAnyRole(AppConstants.ROLE_ADMIN, AppConstants.ROLE_USER, AppConstants.ROLE_VISITOR).anyRequest()
                .authenticated().and().csrf().disable().formLogin().usernameParameter("username")
                .passwordParameter("password").successHandler(successHandler).failureUrl("/login?access-denied").and()
                .logout().invalidateHttpSession(true).and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

}
