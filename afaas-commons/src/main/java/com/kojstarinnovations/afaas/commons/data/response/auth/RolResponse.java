package com.kojstarinnovations.afaas.commons.data.response.auth;

import com.kojstarinnovations.afaas.commons.emuns.RolName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * RolResponse class is a response class that is used to retrieve a Rol.
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolResponse extends AuditAttributeResponseSecurity {
    private Integer id;
    private RolName rolName;
}