package com.spring.security.role.based.service;

import com.spring.security.role.based.model.Role;
import com.spring.security.role.based.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String role) {
        return roleRepository.findByRoleName(role);
    }

    public void createRole(Role createRole) {
        roleRepository.save(createRole);
    }

}
