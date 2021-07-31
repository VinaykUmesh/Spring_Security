package com.spring.security.role.and.privilege.repository;

import com.spring.security.role.and.privilege.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String adminName);

}
