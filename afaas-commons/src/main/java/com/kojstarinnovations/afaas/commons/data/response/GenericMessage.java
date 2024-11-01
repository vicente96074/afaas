package com.kojstarinnovations.afaas.commons.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericMessage<T> {
    private String destination;
    private T content;
}