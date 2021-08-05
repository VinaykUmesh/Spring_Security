package com.spring.security.role.and.privilege.controller;

import com.spring.security.role.and.privilege.exception.UserNameAlreadyExistException;
import com.spring.security.role.and.privilege.model.User;
import com.spring.security.role.and.privilege.service.UserService;
import com.spring.security.role.and.privilege.service.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class ApiController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/app/user-register")
    public ResponseEntity<String> createNewUser(@RequestBody User user) throws UserNameAlreadyExistException {
        userService.createNewUser(user, AppConstants.APP_ROLE_USER);
        return new ResponseEntity<String>("User Created Successfully " + user.getUsername(), HttpStatus.OK);
    }

    @PostMapping(value = "/app/visitor-register")
    public ResponseEntity<String> createNewVisitor(@RequestBody User user) throws UserNameAlreadyExistException {
        userService.createNewUser(user, AppConstants.APP_ROLE_VISITOR);
        return new ResponseEntity<String>("User Created Successfully " + user.getUsername(), HttpStatus.OK);
    }

    @PostMapping(value = "/app/get-user")
    public ResponseEntity<User> postMethodName(@RequestBody User entity) {
        User user = userService.findByUsername(entity.getUsername());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
