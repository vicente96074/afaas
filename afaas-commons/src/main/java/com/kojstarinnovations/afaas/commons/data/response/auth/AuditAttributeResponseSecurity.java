package com.kojstarinnovations.afaas.commons.data.response.auth;

import com.kojstarinnovations.afaas.commons.emuns.ElementStatus;
import com.kojstarinnovations.afaas.commons.emuns.Status;
import com.kojstarinnovations.afaas.commons.emuns.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditAttributeResponseSecurity {
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ElementStatus elementStatus;
    private TransactionStatus transactionStatus;
    private Status status;
    private String userCredentials;
}