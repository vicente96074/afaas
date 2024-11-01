package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity;

import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "access")
public class Access extends AuditAttributeSecurity {

    public Access(@NotNull AccessName accessName){
        this.accessName = accessName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Default value is NONE and is enum
    @Column(name = "access_name")
    @Enumerated(EnumType.STRING)
    private AccessName accessName;
}