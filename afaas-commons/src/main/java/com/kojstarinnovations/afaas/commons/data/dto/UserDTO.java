package com.kojstarinnovations.afaas.commons.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * UserDTO
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO extends AuditAttributeGenericDTO {

    private String id;
    private String storeId;
    private String storeBranchId;

    private String name;
    private String lastName;
    private String identification;
    private String username;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String urlProfilePicture;

    private String phone;
    private String address;

    private Set<RolDTO> rolesDTO = new HashSet<>();
    private Set<AccessDTO> accessesDTO = new HashSet<>();
}