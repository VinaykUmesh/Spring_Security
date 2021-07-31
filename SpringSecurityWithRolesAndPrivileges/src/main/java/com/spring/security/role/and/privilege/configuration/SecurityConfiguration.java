package com.spring.security.role.and.privilege.configuration;

import com.spring.security.role.and.privilege.configuration.service.CustomAccessDeniedHandler;
import com.spring.security.role.and.privilege.configuration.service.CustomSucessHandler;
import com.spring.security.role.and.privilege.configuration.service.CustomUserDetailsService;
import com.spring.security.role.and.privilege.service.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomSucessHandler successHandler;

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

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ADMIN > USER \n USER > VISITOR";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/secure/**").permitAll().antMatchers("/admin/**")
                .hasRole(AppConstants.APP_ROLE_ADMIN).antMatchers("/user/**")
                .hasAnyRole(AppConstants.APP_ROLE_ADMIN, AppConstants.APP_ROLE_USER).antMatchers("/visitor/**")
                .hasAnyRole(AppConstants.APP_ROLE_ADMIN, AppConstants.APP_ROLE_ADMIN, AppConstants.APP_ROLE_USER,
                        AppConstants.APP_ROLE_VISITOR)
                .anyRequest().authenticated().and().csrf().disable().formLogin().usernameParameter("username")
                .passwordParameter("password").successHandler(successHandler).failureUrl("/login?accessdenied").and()
                .logout().invalidateHttpSession(true).and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }
}
