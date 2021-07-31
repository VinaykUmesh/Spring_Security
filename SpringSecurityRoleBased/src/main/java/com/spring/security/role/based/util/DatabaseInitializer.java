package com.spring.security.role.based.util;

import javax.transaction.Transactional;

import com.spring.security.role.based.model.Role;
import com.spring.security.role.based.service.RoleService;
import com.spring.security.role.based.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Value(AppConstants.ADMIN_NAME)
    private String adminName;

    @Value(AppConstants.ADMIN_PASSWORD)
    private String adminPass;

    @Value(AppConstants.ADMIN_EMAIL)
    private String adminEmail;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent arg0) {

        AppConstants.ROLE_SET.forEach(role -> {
            if (roleService.findByRoleName(role) == null) {
                Role createRole = new Role();
                createRole.setRoleName(role);
                roleService.createRole(createRole);
            }
        });

        userService.createAdmin(adminName, adminPass, adminEmail);

    }

}
