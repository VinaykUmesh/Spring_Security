package com.spring.security.role.based.repository;

import com.spring.security.role.based.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsername(String adminName);
    
}
