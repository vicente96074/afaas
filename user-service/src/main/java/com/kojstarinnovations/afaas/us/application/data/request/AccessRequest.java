package com.kojstarinnovations.afaas.us.application.data.request;

import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessRequest {
    private AccessName accessName;
}