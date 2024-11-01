package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccessId implements Serializable {
    private String userId;
    private Integer accessId;
}
