package com.spring.security.role.and.privilege.service;

import com.spring.security.role.and.privilege.model.Role;
import com.spring.security.role.and.privilege.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String role) {
        return roleRepository.findByRoleName(role);
    }

    public void createRole(Role addRole) {
        roleRepository.save(addRole);
    }

}
