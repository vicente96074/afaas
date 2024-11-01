package com.kojstarinnovations.afaas.commons.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationByStoreRequest {
    private String storeBranchId;
    private String storeId;
    private Integer page;
    private Integer size;
    private String orders;
    private Boolean asc;
}
