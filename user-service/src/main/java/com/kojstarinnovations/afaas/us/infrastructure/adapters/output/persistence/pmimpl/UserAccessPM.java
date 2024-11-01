package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl;

import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.commons.pm.PersistenceMapper;
import com.kojstarinnovations.afaas.us.domain.model.UserAccessDTO;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccessPM implements PersistenceMapper<UserAccess, UserAccessDTO> {
    @Override
    public UserAccess dtoToEntity(UserAccessDTO dto) {
        return modelMapper.map(dto, UserAccess.class);
    }

    @Override
    public UserAccessDTO entityToDto(UserAccess entity) {
        return modelMapper.map(entity, UserAccessDTO.class);
    }

    @Autowired
    public UserAccessPM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapperCustomized modelMapper;
}
