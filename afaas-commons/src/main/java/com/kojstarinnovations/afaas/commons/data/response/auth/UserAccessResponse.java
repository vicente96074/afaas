package com.kojstarinnovations.afaas.commons.data.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccessResponse extends AuditAttributeResponseSecurity {
    private String userId;
    private Integer accessId;
}