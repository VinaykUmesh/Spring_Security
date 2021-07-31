package com.spring.security.role.and.privilege.repository;

import com.spring.security.role.and.privilege.model.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {

    public Privilege findByPrivilegeName(String appPrivilege);
    
}
