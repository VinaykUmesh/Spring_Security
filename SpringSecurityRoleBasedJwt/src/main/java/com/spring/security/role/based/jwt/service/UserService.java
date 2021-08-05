package com.spring.security.role.based.jwt.service;

import java.util.Date;

import com.spring.security.role.based.jwt.exception.UserAlreadyExistException;
import com.spring.security.role.based.jwt.model.Role;
import com.spring.security.role.based.jwt.model.User;
import com.spring.security.role.based.jwt.repository.UserRepository;
import com.spring.security.role.based.jwt.util.AppConstants;
import com.spring.security.role.based.jwt.util.AppUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AppUtil appUtil;

    public void createAdmin(String adminName, String adminEmail, String adminPassword) {
        if (userRepository.findByUsername(adminName) == null) {
            Role adminRole = roleService.findByRoleName(AppConstants.ROLE_ADMIN);
            User user = new User(adminName, appUtil.encode(adminPassword), adminEmail, Boolean.TRUE, new Date(),
                    adminRole);
            userRepository.save(user);
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(User entity) throws UserAlreadyExistException {
        if (userRepository.findByUsername(entity.getUsername()) != null)
            throw new UserAlreadyExistException("User account exists");

        Role userRole = roleService.findByRoleName(AppConstants.ROLE_USER);
        User user = new User(entity.getUsername(), appUtil.encode(entity.getPassword()), entity.getEmail(),
                Boolean.TRUE, new Date(), userRole);
        userRepository.save(user);
    }

    public void createVisitor(User entity) throws UserAlreadyExistException {
        if (userRepository.findByUsername(entity.getUsername()) != null)
            throw new UserAlreadyExistException("User account exists");

        Role visitorRole = roleService.findByRoleName(AppConstants.ROLE_VISITOR);
        User user = new User(entity.getUsername(), appUtil.encode(entity.getPassword()), entity.getEmail(),
                Boolean.TRUE, new Date(), visitorRole);
        userRepository.save(user);
    }
}
