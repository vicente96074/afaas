package com.kojstarinnovations.afaas.us.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccessIDDTO {
    private String userId;
    private Integer accessId;
}
