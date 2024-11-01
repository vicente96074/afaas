package com.kojstarinnovations.afaas.commons.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileRequest {
    private String filename;
    private String category;
}
