package com.kojstarinnovations.afaas.commons.data.response.auth;

import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessResponse extends AuditAttributeResponseSecurity {
    private Integer id;
    private AccessName accessName;
}