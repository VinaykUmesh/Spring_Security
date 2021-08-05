package com.spring.security.role.based.jwt.controller;

import java.util.ArrayList;

import com.spring.security.role.based.jwt.util.AppUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private AppUtil appUtil;

    @GetMapping(value = "/admin/home")
    public ResponseEntity<String> getAdmin(Authentication auth) {
        return new ResponseEntity<String>("Welcome " + appUtil.getUserName() + " your role is "
                + new ArrayList<>(auth.getAuthorities()).get(0).getAuthority(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/home")
    public ResponseEntity<String> getUser(Authentication auth) {
        return new ResponseEntity<String>("Welcome " + appUtil.getUserName() + " your role is "
                + new ArrayList<>(auth.getAuthorities()).get(0).getAuthority(), HttpStatus.OK);
    }

    @GetMapping(value = "/visitor/home")
    public ResponseEntity<String> getVisitor(Authentication auth) {
        return new ResponseEntity<String>("Welcome " + appUtil.getUserName() + " your role is "
                + new ArrayList<>(auth.getAuthorities()).get(0).getAuthority(), HttpStatus.OK);
    }
}
