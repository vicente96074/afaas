package com.kojstarinnovations.afaas.us.domain.dmimpl;

import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;
import com.kojstarinnovations.afaas.commons.data.response.auth.AccessResponse;
import com.kojstarinnovations.afaas.commons.data.response.auth.RolResponse;
import com.kojstarinnovations.afaas.commons.data.response.auth.UserResponse;
import com.kojstarinnovations.afaas.commons.dm.DomainMapper;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.us.application.data.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * UserDM maps a user request to a user dto and a user dto to a user response
 *
 * @author Augusto Vicente
 */
@Component
public class UserDM implements DomainMapper<UserDTO, UserRequest, UserResponse> {

    /**
     * Maps a user request to a user dto
     *
     * @param userRequest the request to be mapped
     * @return the mapped dto
     */
    @Override
    public UserDTO requestToDto(UserRequest userRequest) {
        return modelMapper.map(userRequest, UserDTO.class);
    }

    /**
     * Maps a user dto to a user response
     *
     * @param dto the dto to be mapped
     * @return the mapped response
     */
    @Override
    public UserResponse dtoToResponse(UserDTO dto) {
        UserResponse response = modelMapper.map(dto, UserResponse.class);

        if (dto.getRolesDTO() != null && !dto.getRolesDTO().isEmpty()) {
            response.setRolResponses(
                    dto.getRolesDTO().stream()
                            .map(rolDTO -> modelMapper.map(rolDTO, RolResponse.class))
                            .collect(Collectors.toSet())
            );
        }

        if (dto.getAccessesDTO() != null && !dto.getAccessesDTO().isEmpty()) {
            response.setAccessResponses(
                    dto.getAccessesDTO().stream()
                            .map(accessDTO -> modelMapper.map(accessDTO, AccessResponse.class))
                            .collect(Collectors.toSet())
            );
        }

        return response;
    }

    @Autowired
    public UserDM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapperCustomized modelMapper;
}