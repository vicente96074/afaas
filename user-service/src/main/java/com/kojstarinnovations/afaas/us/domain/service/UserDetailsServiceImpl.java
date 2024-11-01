package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.dto.PrincipalUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    public PrincipalUser principalUserFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getPrincipal() instanceof PrincipalUser ? (PrincipalUser) authentication.getPrincipal() : null;
    }
}
