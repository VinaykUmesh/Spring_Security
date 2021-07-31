package com.spring.security.role.based.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.role.based.exception.UserAlreadyExistException;
import com.spring.security.role.based.model.User;
import com.spring.security.role.based.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/secure/app")
public class ApiController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/create-user-account")
    public ResponseEntity<String> createUser(@RequestBody User entity) throws UserAlreadyExistException {
        userService.createUser(entity);
        return  new ResponseEntity<>("User Created : "+entity.getUsername(),HttpStatus.CREATED);
    }

    @PostMapping(value="/create-visitor-account")
    public ResponseEntity<String> createVisitor(@RequestBody User entity) throws UserAlreadyExistException {
        userService.createVisitor(entity);
        return  new ResponseEntity<>("User Created : "+entity.getUsername(),HttpStatus.CREATED);
    }

    @PostMapping(value = "/get-user")
    public ResponseEntity<User> postMethodName2(@RequestBody User entity) {
        User user = userService.findByUsername(entity.getUsername());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
}
