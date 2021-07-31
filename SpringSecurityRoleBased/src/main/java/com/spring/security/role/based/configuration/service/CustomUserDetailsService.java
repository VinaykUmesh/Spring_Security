package com.spring.security.role.based.configuration.service;

import com.spring.security.role.based.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private com.spring.security.role.based.service.UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByUsername(username);
            return new CustomUserDetails(user);
        } catch (UsernameNotFoundException unfex) {
            unfex.printStackTrace();
            throw new UsernameNotFoundException("Bad Credentials");
        }
    }

}
