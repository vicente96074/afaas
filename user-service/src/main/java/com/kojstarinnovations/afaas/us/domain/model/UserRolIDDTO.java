package com.kojstarinnovations.afaas.us.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserRolIDDTO
 *
 * @author Augusto Vicente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolIDDTO {
    private String userId;
    private Integer rolId;
}