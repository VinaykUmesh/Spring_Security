package com.spring.security.role.and.privilege.configuration.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.spring.security.role.and.privilege.model.User;
import com.spring.security.role.and.privilege.service.util.AppConstants;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public CustomUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(AppConstants.APP_ROLE_PREFIX + user.getRoles().getRoleName()));
        user.getRoles().getPrivileges().forEach(privilege -> {
            authorities.add(new SimpleGrantedAuthority(privilege.getPrivilegeName()));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }

    public Date createdAt() {
        return user.getCreatedAt();
    }

}
