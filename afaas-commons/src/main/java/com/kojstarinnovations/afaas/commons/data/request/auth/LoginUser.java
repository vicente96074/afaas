package com.kojstarinnovations.afaas.commons.data.request.auth;

import com.kojstarinnovations.afaas.commons.validation.DataRequired;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginUser
 *
 * @author Augusto Vicente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUser {

    @DataRequired(message = "Username es requerido")
    private String username;

    @DataRequired(message = "Password es requerido")
    private String password;
}
