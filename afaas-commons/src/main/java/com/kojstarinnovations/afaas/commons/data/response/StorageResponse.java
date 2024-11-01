package com.kojstarinnovations.afaas.commons.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StorageResponse {
    private String filename;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
