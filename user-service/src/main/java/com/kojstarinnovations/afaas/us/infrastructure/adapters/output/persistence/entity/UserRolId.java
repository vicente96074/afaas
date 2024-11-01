package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserRolId class is used to create a composite primary key for the UserRol entity
 *
 * @author BalamKiche
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolId implements Serializable {
    private String userId;
    private Integer rolId;
}
