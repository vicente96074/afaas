package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "user_access")
@IdClass(UserAccessId.class)
public class UserAccess extends AuditAttributeSecurity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "access_id")
    private Integer accessId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Access access;
}
