package com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.entity;

import com.kojstarinnovations.afaas.commons.emuns.RolName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Rol Entity Class Definition - JPA to represent the rol table in the database
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rol")
public class Rol extends AuditAttributeSecurity {

    public Rol(@NotNull RolName rolName){
        this.rolName = rolName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_name", nullable = false)
    private RolName rolName;
}