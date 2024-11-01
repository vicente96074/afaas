package com.kojstarinnovations.afaas.us.application.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserRolRequest class definition to be used as a request body for the UserRolController class
 *
 * @author Augusto Vicente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolRequest {
    private String userId;
    private Integer rolId;
}
