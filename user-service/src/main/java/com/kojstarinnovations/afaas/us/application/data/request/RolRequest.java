package com.kojstarinnovations.afaas.us.application.data.request;

import com.kojstarinnovations.afaas.commons.emuns.RolName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RolRequest class is a request class that is used to create a new Rol.
 *
 * @author Augusto Vicente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolRequest {

    private RolName rolName;
}
