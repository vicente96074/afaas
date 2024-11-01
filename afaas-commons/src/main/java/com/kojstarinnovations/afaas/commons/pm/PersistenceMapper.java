package com.kojstarinnovations.afaas.commons.pm;

import org.mapstruct.Mapper;

/**
 * PersistenceMapper
 *
 * @author Augusto Vicente
 * @param <ENTITY>
 * @param <DTO>
 */
@Mapper
public interface PersistenceMapper<ENTITY, DTO> {

    /**
     * The method dtoToEntity
     * @param dto is the data transfer object
     * @return the entity
     */
    public ENTITY dtoToEntity(DTO dto);

    /**
     * The method entityToDto
     *
     * @param entity is the entity
     * @return the data transfer object
     */
    public DTO entityToDto(ENTITY entity);
}
