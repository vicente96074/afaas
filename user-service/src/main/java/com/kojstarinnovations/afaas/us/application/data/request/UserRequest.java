package com.kojstarinnovations.afaas.us.application.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kojstarinnovations.afaas.commons.validation.DataRequired;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * UserRequest
 *
 * @author Augusto Vicente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    @DataRequired(message = "El nombre del usuario es requerido")
    private String name;

    @DataRequired(message = "El apellido del usuario es requerido")
    private String lastName;

    @DataRequired(message = "El DPI del usuario es requerido")
    private String identification;

    @DataRequired(message = "El username del usuario es requerido")
    private String username;

    @DataRequired(message = "El correo del usuario es requerido")
    @Email(message = "Email should be valid")
    private String email;

    @DataRequired(message = "La contraseña del usuario es requerida")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String urlProfilePicture;

    private String storeBranchId;
    private String storeId;
    private String phone;
    private String address;

    private Set<String> rolesRequest = new HashSet<>();
    private Set<String> accessesRequest = new HashSet<>();
}