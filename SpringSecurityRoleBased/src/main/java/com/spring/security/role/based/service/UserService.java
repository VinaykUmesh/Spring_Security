package com.spring.security.role.based.service;

import java.util.Date;

import com.spring.security.role.based.exception.UserAlreadyExistException;
import com.spring.security.role.based.model.Role;
import com.spring.security.role.based.model.User;
import com.spring.security.role.based.repository.UserRepository;
import com.spring.security.role.based.util.AppConstants;
import com.spring.security.role.based.util.AppUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private RoleService roleService;

    public void createAdmin(String adminName, String adminPass, String adminEmail) {

        if (userRepository.findByUsername(adminName) == null) {
            Role adminRole = roleService.findByRoleName(AppConstants.ROLE_ADMIN);
            User user = new User();
            user.setUsername(adminName);
            user.setPassword(appUtil.encode(adminPass));
            user.setEmail(adminEmail);
            user.setCreatedAt(new Date());
            user.setRoles(adminRole);

            userRepository.save(user);
        }
    }

    public void createUser(User entity) throws UserAlreadyExistException {

        if (userRepository.findByUsername(entity.getUsername()) != null)
            throw new UserAlreadyExistException("User with this name exist");

        Role userRole = roleService.findByRoleName(AppConstants.ROLE_USER);
        User newUser = new User();
        newUser.setUsername(entity.getUsername());
        newUser.setPassword(appUtil.encode(entity.getPassword()));
        newUser.setEmail(entity.getEmail());
        newUser.setCreatedAt(new Date());
        newUser.setRoles(userRole);

        userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createVisitor(User entity) throws UserAlreadyExistException {

        if (userRepository.findByUsername(entity.getUsername()) != null)
            throw new UserAlreadyExistException("User with this name exist");

        Role userRole = roleService.findByRoleName(AppConstants.ROLE_VISITOR);
        User newUser = new User();
        newUser.setUsername(entity.getUsername());
        newUser.setPassword(appUtil.encode(entity.getPassword()));
        newUser.setEmail(entity.getEmail());
        newUser.setCreatedAt(new Date());
        newUser.setRoles(userRole);

        userRepository.save(newUser);
    }

}
