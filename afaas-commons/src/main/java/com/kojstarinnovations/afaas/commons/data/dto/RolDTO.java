package com.kojstarinnovations.afaas.commons.data.dto;

import com.kojstarinnovations.afaas.commons.emuns.RolName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * RolDTO
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolDTO extends AuditAttributeGenericDTO {

    public RolDTO(RolName rolName) {
        this.rolName = rolName;
    }

    private Integer id;
    private RolName rolName;
}