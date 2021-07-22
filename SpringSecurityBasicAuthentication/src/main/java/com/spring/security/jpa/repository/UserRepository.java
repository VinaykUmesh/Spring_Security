package com.spring.security.jpa.repository;

import com.spring.security.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("SELECT u FROM User u WHERE email = :email")
    public User findUserByEmail(@Param("email") String email);
    
}
