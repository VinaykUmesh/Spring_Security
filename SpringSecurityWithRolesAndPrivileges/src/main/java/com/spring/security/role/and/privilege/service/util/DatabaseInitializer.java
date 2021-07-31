package com.spring.security.role.and.privilege.service.util;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.spring.security.role.and.privilege.model.Privilege;
import com.spring.security.role.and.privilege.model.Role;
import com.spring.security.role.and.privilege.service.PrivilegeService;
import com.spring.security.role.and.privilege.service.RoleService;
import com.spring.security.role.and.privilege.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @Value(AppConstants.DEFAULT_ADMIN_NAME)
    private String adminName;

    @Value(AppConstants.DEFAULT_ADMIN_EMAIL)
    private String adminEmail;

    @Value(AppConstants.DEFAULT_ADMIN_PASSWORD)
    private String adminPass;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Privilege readPrivilege = CreatePrivilegeIfNotExist(AppConstants.APP_PRIVILEGE_READ);
        Privilege writePrivilege = CreatePrivilegeIfNotExist(AppConstants.APP_PRIVILEGE_WRITE);
        Privilege deletePrivilege = CreatePrivilegeIfNotExist(AppConstants.APP_PRIVILEGE_DELETE);

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, deletePrivilege);
        List<Privilege> userPrivileges = Arrays.asList(readPrivilege, writePrivilege, deletePrivilege);
        List<Privilege> visitorPrivileges = Arrays.asList(readPrivilege);

        createRoleIfNotExist(AppConstants.APP_ROLE_ADMIN, adminPrivileges);
        createRoleIfNotExist(AppConstants.APP_ROLE_USER, userPrivileges);
        createRoleIfNotExist(AppConstants.APP_ROLE_VISITOR, visitorPrivileges);

        userService.createAdminUser(adminName, adminEmail, adminPass);
    }

    @Transactional
    private Role createRoleIfNotExist(String role, List<Privilege> privileges) {
        Role roles = roleService.findByRoleName(role);
        if (roles == null) {
            roles = new Role(role);
            roles.setPrivileges(privileges);
            roleService.createRole(roles);
        }
        return roles;
    }

    @Transactional
    private Privilege CreatePrivilegeIfNotExist(String name) {

        Privilege privilege = privilegeService.findByPrivilegeName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeService.createPrivilege(privilege);
        }
        return privilege;
    }

}
