package com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * UserRol Entity Class Definition - JPA to represent the user_rol table in the database
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_rol")
@IdClass(UserRolId.class)
public class UserRol extends AuditAttributeSecurity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "rol_id")
    private Integer rolId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Rol rol;
}