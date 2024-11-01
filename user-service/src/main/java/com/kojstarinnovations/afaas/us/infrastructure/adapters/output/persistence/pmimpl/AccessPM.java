package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl;

import com.kojstarinnovations.afaas.commons.data.dto.AccessDTO;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.commons.pm.PersistenceMapper;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessPM implements PersistenceMapper<Access, AccessDTO> {
    /**
     * The method dtoToEntity
     *
     * @param accessDTO is the data transfer object
     * @return the entity
     */
    @Override
    public Access dtoToEntity(AccessDTO accessDTO) {
        return modelMapper.map(accessDTO, Access.class);
    }

    /**
     * The method entityToDto
     *
     * @param access is the entity
     * @return the data transfer object
     */
    @Override
    public AccessDTO entityToDto(Access access) {
        return modelMapper.map(access, AccessDTO.class);
    }

    @Autowired
    public AccessPM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    private final ModelMapperCustomized modelMapper;
}