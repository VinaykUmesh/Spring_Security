package com.spring.security.role.and.privilege.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUtil {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public final String encode(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public final String getUserName() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
