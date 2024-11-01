package com.kojstarinnovations.afaas.commons.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfficeRequest {
    private String storeBranchId;
    private String branchOffice;
    private String nitOffice;
    private String phoneOffice;
}
