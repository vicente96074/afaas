package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl;

import com.kojstarinnovations.afaas.commons.data.dto.RolDTO;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.commons.pm.PersistenceMapper;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RolPersistenceMapper
 *
 * @author Augusto Vicente
 */
@Component
public class RolPM implements PersistenceMapper<Rol, RolDTO> {

    /**
     * Maps a RolDTO to a Rol entity
     *
     * @param dto the data transfer object
     * @return the entity
     */
    @Override
    public Rol dtoToEntity(RolDTO dto) {
        return modelMapper.map(dto, Rol.class);
    }

    /**
     * Maps a Rol entity to a RolDTO
     *
     * @param entity the entity
     * @return the data transfer object
     */
    @Override
    public RolDTO entityToDto(Rol entity) {
        return modelMapper.map(entity, RolDTO.class);
    }

    @Autowired
    public RolPM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapperCustomized modelMapper;
}