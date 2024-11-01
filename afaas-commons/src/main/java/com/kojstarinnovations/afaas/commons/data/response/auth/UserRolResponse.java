package com.kojstarinnovations.afaas.commons.data.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * UserRolResponse class definition to be used as a response body for the UserRolController class
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolResponse extends AuditAttributeResponseSecurity {
    private String userId;
    private Integer rolId;
}