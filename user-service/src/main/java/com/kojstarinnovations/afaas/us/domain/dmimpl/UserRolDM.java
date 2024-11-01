package com.kojstarinnovations.afaas.us.domain.dmimpl;

import com.kojstarinnovations.afaas.commons.data.response.auth.UserRolResponse;
import com.kojstarinnovations.afaas.commons.dm.DomainMapper;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.us.application.data.request.UserRolRequest;
import com.kojstarinnovations.afaas.us.domain.model.UserRolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserRolDM maps a user rol request to a user rol dto and a user rol dto to a user rol response
 *
 * @author Augusto Vicente
 */
@Component
public class UserRolDM implements DomainMapper<UserRolDTO, UserRolRequest, UserRolResponse> {


    /**
     * Maps a user rol request to a user rol dto
     *
     * @param userRolRequest the request to be mapped
     * @return the mapped dto
     */
    @Override
    public UserRolDTO requestToDto(UserRolRequest userRolRequest) {
        return modelMapper.map(userRolRequest, UserRolDTO.class);
    }

    /**
     * Maps a user rol dto to a user rol response
     *
     * @param userRolDto the dto to be mapped
     * @return the mapped response
     */
    @Override
    public UserRolResponse dtoToResponse(UserRolDTO userRolDto) {
        return modelMapper.map(userRolDto, UserRolResponse.class);
    }

    @Autowired
    public UserRolDM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapperCustomized modelMapper;
}