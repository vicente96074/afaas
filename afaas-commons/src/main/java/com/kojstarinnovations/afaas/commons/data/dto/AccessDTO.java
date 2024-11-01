package com.kojstarinnovations.afaas.commons.data.dto;

import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessDTO extends AuditAttributeGenericDTO {
    private Integer id;
    private AccessName accessName;
}