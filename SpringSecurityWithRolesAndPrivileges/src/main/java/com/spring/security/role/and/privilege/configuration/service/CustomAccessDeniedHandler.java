package com.spring.security.role.and.privilege.configuration.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = new ArrayList<>(auth.getAuthorities());

        if (!(auth instanceof AnonymousAuthenticationToken) && authorities.get(0).getAuthority().equals("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/admin/home");
        }
        if (!(auth instanceof AnonymousAuthenticationToken) && authorities.get(0).getAuthority().equals("ROLE_USER")) {
            response.sendRedirect(request.getContextPath() + "/user/home");
        }
        if (!(auth instanceof AnonymousAuthenticationToken) && authorities.get(0).getAuthority().equals("ROLE_VISITOR")) {
            response.sendRedirect(request.getContextPath() + "/visitor/home");
        } else {
            response.sendRedirect(request.getContextPath() + "/login?access-denied");
        }
    }

}
