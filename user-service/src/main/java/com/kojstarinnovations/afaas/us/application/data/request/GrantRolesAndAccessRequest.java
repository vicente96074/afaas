package com.kojstarinnovations.afaas.us.application.data.request;

import com.kojstarinnovations.afaas.commons.validation.DataRequired;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrantRolesAndAccessRequest {
    private List<String> roles;
    private List<String> accesses;

    @DataRequired(message = "User id is required")
    private String userId;

    @DataRequired(message = "Store id is required")
    private String storeBranchId;

    @DataRequired(message = "Store id is required")
    private String storeId;
}