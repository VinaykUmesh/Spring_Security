package com.spring.security.role.and.privilege.repository;

import com.spring.security.role.and.privilege.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByRoleName(String role);

}
