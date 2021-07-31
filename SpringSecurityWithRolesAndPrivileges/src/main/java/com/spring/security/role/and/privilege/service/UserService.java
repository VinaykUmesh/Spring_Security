package com.spring.security.role.and.privilege.service;

import java.util.Date;

import com.spring.security.role.and.privilege.exception.UserNameAlreadyExistException;
import com.spring.security.role.and.privilege.model.Role;
import com.spring.security.role.and.privilege.model.User;
import com.spring.security.role.and.privilege.repository.RoleRepository;
import com.spring.security.role.and.privilege.repository.UserRepository;
import com.spring.security.role.and.privilege.service.util.AppConstants;
import com.spring.security.role.and.privilege.service.util.AppUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppUtil appUtil;

    public void createAdminUser(String adminName, String adminEmail, String adminPass) {
        Role adminRole = roleRepository.findByRoleName(AppConstants.APP_ROLE_ADMIN);
        if (userRepository.findByUsername(adminName) == null) {
            User createAdmin = new User();
            createAdmin.setUsername(adminName);
            createAdmin.setPassword(appUtil.encode(adminPass));
            createAdmin.setEmail(adminEmail);
            createAdmin.setCreatedAt(new Date());
            createAdmin.setRoles(adminRole);
            userRepository.save(createAdmin);
        }
    }

    public void createNewUser(User user, String role) throws UserNameAlreadyExistException {
        User newUser = userRepository.findByUsername(user.getUsername());
        if (newUser != null)
            throw new UserNameAlreadyExistException("User with this name has already registered with us.");

        Role userRole = roleRepository.findByRoleName(role);
        newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(appUtil.encode(user.getPassword()));
        newUser.setCreatedAt(new Date());
        newUser.setRoles(userRole);
        userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
