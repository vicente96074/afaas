package com.kojstarinnovations.afaas.commons.dm;

import org.mapstruct.Mapper;

/**
 * DomainMapper
 *
 * @param <DTO>
 * @param <REQUEST>
 * @param <RESPONSE>
 *
 * @author Augusto Vicente
 */
@Mapper
public interface DomainMapper<DTO, REQUEST, RESPONSE> {

    /**
     * A method to map a request to a dto
     *
     * @param request the request to be mapped
     * @return the mapped dto
     */
    DTO requestToDto(REQUEST request);

    /**
     * A method to map a dto to a response
     *
     * @param dto the dto to be mapped
     * @return the mapped response
     */
    RESPONSE dtoToResponse(DTO dto);
}
