package com.spring.security.role.based.jwt.configuration.util;

import com.spring.security.role.based.jwt.model.User;
import com.spring.security.role.based.jwt.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userService.findByUsername(username);
            return new CustomUserDetails(user);
        }catch (UsernameNotFoundException ufex){
            throw new UsernameNotFoundException("Bad Credentials.");
        }
    }
}
