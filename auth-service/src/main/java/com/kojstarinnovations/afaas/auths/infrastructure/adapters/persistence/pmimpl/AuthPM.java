package com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.pmimpl;

import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.entity.User;
import com.kojstarinnovations.afaas.commons.data.dto.AccessDTO;
import com.kojstarinnovations.afaas.commons.data.dto.RolDTO;
import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.commons.pm.PersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * UserPersistenceMapper
 * This class maps the UserDTO to the User entity and vice versa
 *
 * @author BalamKiche
 */
@Component
public class AuthPM implements PersistenceMapper<User, UserDTO> {

    /**
     * Maps a UserDTO to a User entity
     *
     * @param dto the data transfer object
     * @return User entity
     */
    @Override
    public User dtoToEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    /**
     * Maps a User entity to a UserDTO
     *
     * @param entity the entity
     * @return UserDTO data transfer object
     */
    @Override
    public UserDTO entityToDto(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserDTO entityToDtoWithAccessAndRoles(User entity) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);

        if (entity.getAccesses() != null && !entity.getAccesses().isEmpty()) {
            dto.setAccessesDTO(entity.getAccesses().stream()
                    .map(access -> modelMapper.map(access, AccessDTO.class))
                    .collect(Collectors.toSet())
            );
        }

        if (entity.getRoles() != null && !entity.getRoles().isEmpty()) {
            dto.setRolesDTO(entity.getRoles().stream()
                    .map(rol -> modelMapper.map(rol, RolDTO.class))
                    .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    @Autowired
    public AuthPM(
            ModelMapperCustomized modelMapper
    ) {
        this.modelMapper = modelMapper;
    }

    /**
     * Autowired ModelMapper
     */
    private final ModelMapperCustomized modelMapper;
}