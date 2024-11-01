package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl;

import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.commons.pm.PersistenceMapper;
import com.kojstarinnovations.afaas.us.domain.model.UserRolDTO;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.UserRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserRolPersistenceMapper
 *
 * @author BalamKiche
 */
@Component
public class UserRolPM implements PersistenceMapper<UserRol, UserRolDTO> {

    /**
     * Maps a UserRolDTO to a UserRol entity
     *
     * @param dto the data transfer object
     * @return UserRol entity
     */
    @Override
    public UserRol dtoToEntity(UserRolDTO dto) {
        return modelMapper.map(dto, UserRol.class);
    }

    /**
     * Maps a UserRol entity to a UserRolDTO
     *
     * @param entity the entity
     * @return UserRolDTO data transfer object
     */
    @Override
    public UserRolDTO entityToDto(UserRol entity) {
        return modelMapper.map(entity, UserRolDTO.class);
    }

    @Autowired
    public UserRolPM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }


    private final ModelMapperCustomized modelMapper;
}
