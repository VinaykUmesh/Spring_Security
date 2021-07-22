package com.spring.security.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.jpa.entity.User;
import com.spring.security.jpa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;
    
    @PostMapping(value="/register")
    public String createUser(@RequestBody User entity) {
        User user = new User();
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        userService.createUser(user);
        return new String("User Registered Successfully");
    }
    
}
