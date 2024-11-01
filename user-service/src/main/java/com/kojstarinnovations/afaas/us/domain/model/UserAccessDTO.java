package com.kojstarinnovations.afaas.us.domain.model;

import com.kojstarinnovations.afaas.commons.data.dto.AuditAttributeGenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccessDTO extends AuditAttributeGenericDTO {
    private String userId;
    private Integer accessId;
}