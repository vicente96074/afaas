package com.kojstarinnovations.afaas.us.application.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccessRequest {
    private Integer id;
    private String userId;
    private Integer accessId;
}
