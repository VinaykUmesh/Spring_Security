package com.spring.security.jpa.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app")
public class AppContorller {

    @GetMapping(value = "/home")
    public String homePage(Authentication auth) {
        return "Hello " + auth.getName();
    }

}
