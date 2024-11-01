package com.kojstarinnovations.afaas.us.domain.model;

import com.kojstarinnovations.afaas.commons.data.dto.AuditAttributeGenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * UserRolDTO
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolDTO extends AuditAttributeGenericDTO {
    private String userId;
    private Integer rolId;
}