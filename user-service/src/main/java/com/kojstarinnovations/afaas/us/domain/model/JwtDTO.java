package com.kojstarinnovations.afaas.us.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JwtDTO
 *
 * @author Augusto Vicente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtDTO {
    private String token;
}