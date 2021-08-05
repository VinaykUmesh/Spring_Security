package com.spring.security.role.based.jwt.service;

import com.spring.security.role.based.jwt.model.Role;
import com.spring.security.role.based.jwt.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String role) {
        return roleRepository.findByRoleName(role);
    }

    public void createRole(Role newRole) {
        roleRepository.save(newRole);
    }

}
