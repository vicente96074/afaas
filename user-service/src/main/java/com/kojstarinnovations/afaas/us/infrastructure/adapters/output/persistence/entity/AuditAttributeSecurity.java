package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity;

import com.kojstarinnovations.afaas.commons.emuns.ElementStatus;
import com.kojstarinnovations.afaas.commons.emuns.Status;
import com.kojstarinnovations.afaas.commons.emuns.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class AuditAttributeSecurity {

    //Attributes for audit purposes
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "element_status", columnDefinition = "ENUM('MIGRATED', 'NEW', 'UPDATED', 'DELETED') DEFAULT 'NEW'")
    @Enumerated(EnumType.STRING)
    private ElementStatus elementStatus;

    @Column(name = "transaction_status", columnDefinition = "ENUM('PENDING', 'APPROVED', 'REJECTED', 'CANCELLED', 'COMPLETED', 'REVERSED') DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "status", columnDefinition = "ENUM('ACTIVE', 'INACTIVE', 'VALID', 'EXPIRED', 'APPROVED', 'REJECTED') DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status;
}