package com.spring.security.role.based.configuration.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.security.role.based.util.AppConstants;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {

        List<GrantedAuthority> authorities = new ArrayList<>(auth.getAuthorities());

        if (!(auth instanceof AnonymousAuthenticationToken) && authorities.get(0).getAuthority().equals(AppConstants.ROLE_STRING_PREFIX+AppConstants.ROLE_ADMIN)) {
            response.sendRedirect(request.getContextPath() + "/admin/home");
        } else if (!(auth instanceof AnonymousAuthenticationToken)
                && authorities.get(0).getAuthority().equals(AppConstants.ROLE_STRING_PREFIX+AppConstants.ROLE_USER)) {
            response.sendRedirect(request.getContextPath() + "/user/home");
        } else if (!(auth instanceof AnonymousAuthenticationToken)
                && authorities.get(0).getAuthority().equals(AppConstants.ROLE_STRING_PREFIX+AppConstants.ROLE_VISITOR)) {
            response.sendRedirect(request.getContextPath() + "/visitor/home");
        } else {
            response.sendRedirect(request.getContextPath() + "/login?access-denied");
        }
    }

}
