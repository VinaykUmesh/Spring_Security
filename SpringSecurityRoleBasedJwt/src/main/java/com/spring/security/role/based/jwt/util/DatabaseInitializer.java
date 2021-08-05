package com.spring.security.role.based.jwt.util;

import com.spring.security.role.based.jwt.model.Role;
import com.spring.security.role.based.jwt.service.RoleService;
import com.spring.security.role.based.jwt.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        AppConstants.ROLE_SET.forEach(role -> {
            if (roleService.findByRoleName(role) == null) {
                Role newRole = new Role(role);
                roleService.createRole(newRole);
            }
        });

        userService.createAdmin(AppConstants.ADMIN_NAME, AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);

    }

}
