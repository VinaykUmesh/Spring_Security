package com.spring.security.jpa.util;

import com.spring.security.jpa.entity.User;
import com.spring.security.jpa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userService.findUserByEmail(email);
            return new CustomUserDetails(user);
        } catch (UsernameNotFoundException unfex) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }
}
