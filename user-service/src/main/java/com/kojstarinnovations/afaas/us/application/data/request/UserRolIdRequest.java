package com.kojstarinnovations.afaas.us.application.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserRolIdRequest
 *
 * @author Augusto vicente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolIdRequest {
    private String userId;
    private Integer rolId;
}
