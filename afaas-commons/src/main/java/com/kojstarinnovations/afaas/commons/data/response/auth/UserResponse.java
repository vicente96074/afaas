package com.kojstarinnovations.afaas.commons.data.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * UserResponse class definition to be used as a response body for the user controller
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse extends AuditAttributeResponseSecurity {

    private String id;
    private String name;
    private String lastName;
    private String identification;
    private String username;
    private String email;
    private LocalDate birthDate;

    private String storeId;
    private String storeBranchId;
    private String urlProfilePicture;
    private String phone;
    private String address;

    private Set<RolResponse> rolResponses = new HashSet<>();
    private Set<AccessResponse> accessResponses = new HashSet<>();
}