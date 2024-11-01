package com.kojstarinnovations.afaas.us.application.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccessIdRequest {
    private String userId;
    private Integer accessId;
}
