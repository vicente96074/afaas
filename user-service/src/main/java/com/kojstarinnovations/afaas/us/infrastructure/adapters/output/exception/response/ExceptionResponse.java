package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ExceptionResponse
 *
 * @author Augusto Vicente
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {

    private LocalDateTime date;
    private String message;
    private List<String> details;
}
