package com.spring.security.role.and.privilege.service;

import com.spring.security.role.and.privilege.model.Privilege;
import com.spring.security.role.and.privilege.repository.PrivilegeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Privilege findByPrivilegeName(String appPrivilege) {
        return privilegeRepository.findByPrivilegeName(appPrivilege);
    }

    public void createPrivilege(Privilege addPrivilege) {
        privilegeRepository.save(addPrivilege);
    }
    
}
