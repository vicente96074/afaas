package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.dto.AuditAttributeGenericDTO;
import com.kojstarinnovations.afaas.commons.data.dto.PrincipalUser;
import com.kojstarinnovations.afaas.commons.emuns.ElementStatus;
import com.kojstarinnovations.afaas.commons.emuns.Status;
import com.kojstarinnovations.afaas.commons.emuns.TransactionStatus;
import com.kojstarinnovations.afaas.commons.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AuditAttributeServiceSecurity {

    public AuditAttributeGenericDTO getAuditAttributesForNew(AuditAttributeGenericDTO dto) {
        PrincipalUser principalUser = userDetailsService.principalUserFromAuthentication();

        if (principalUser == null) {
            throw new UserNotFoundException("No user found in the security context");
        }

        dto.setCreatedBy(principalUser.getCredentials());
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUpdatedBy(principalUser.getCredentials());
        dto.setUpdatedAt(LocalDateTime.now());
        dto.setElementStatus(ElementStatus.NEW);
        dto.setStatus(Status.ACTIVE);
        dto.setTransactionStatus(TransactionStatus.COMPLETED);
        dto.setUserCredentials(principalUser.getCredentials());

        return dto;
    }

    public AuditAttributeGenericDTO getAuditAttributesForSystem(AuditAttributeGenericDTO dto) {
        dto.setCreatedBy("SYSTEM");
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUpdatedBy("SYSTEM");
        dto.setUpdatedAt(LocalDateTime.now());
        dto.setElementStatus(ElementStatus.NEW);
        dto.setTransactionStatus(TransactionStatus.COMPLETED);
        dto.setStatus(Status.ACTIVE);
        dto.setUserCredentials("SYSTEM");

        return dto;
    }

    public AuditAttributeGenericDTO getAuditAttributesForUpdate(AuditAttributeGenericDTO dto) {
        PrincipalUser principalUser = userDetailsService.principalUserFromAuthentication();

        if (principalUser == null) {
            throw new UserNotFoundException("No user found in the security context");
        }

        dto.setUpdatedBy(principalUser.getCredentials());
        dto.setUpdatedAt(LocalDateTime.now());
        dto.setElementStatus(ElementStatus.UPDATED);
        dto.setTransactionStatus(TransactionStatus.COMPLETED);
        dto.setStatus(Status.ACTIVE);
        dto.setUserCredentials(principalUser.getCredentials());

        return dto;
    }

    @Autowired
    public AuditAttributeServiceSecurity(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private final UserDetailsServiceImpl userDetailsService;
}