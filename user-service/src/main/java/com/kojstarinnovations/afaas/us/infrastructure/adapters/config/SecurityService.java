package com.kojstarinnovations.afaas.us.infrastructure.adapters.config;

import com.kojstarinnovations.afaas.commons.data.dto.PrincipalUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("securityService")
public class SecurityService {

    public boolean hasAccess(String access) {
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> accesses = principalUser.getAccesses();

        //Validate if the user has the access
        for (GrantedAuthority grantedAuthority : accesses) {
            if (grantedAuthority.getAuthority().equals(access)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRole(String role) {
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> roles = principalUser.getAuthorities();

        //Validate if the user has the role
        for (GrantedAuthority grantedAuthority : roles) {
            if (grantedAuthority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

    private Authentication authentication;
}
