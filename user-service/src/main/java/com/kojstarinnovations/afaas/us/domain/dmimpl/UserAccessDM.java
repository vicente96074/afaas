package com.kojstarinnovations.afaas.us.domain.dmimpl;

import com.kojstarinnovations.afaas.commons.data.response.auth.UserAccessResponse;
import com.kojstarinnovations.afaas.commons.dm.DomainMapper;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.us.application.data.request.UserAccessRequest;
import com.kojstarinnovations.afaas.us.domain.model.UserAccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccessDM implements DomainMapper<UserAccessDTO, UserAccessRequest, UserAccessResponse> {
    /**
     * Maps a user access request to a user access dto
     *
     * @param userAccessRequest the request to be mapped
     * @return the mapped dto
     */
    @Override
    public UserAccessDTO requestToDto(UserAccessRequest userAccessRequest) {
        return modelMapper.map(userAccessRequest, UserAccessDTO.class);
    }

    /**
     * Maps a user access dto to a user access response
     *
     * @param userAccessDto the dto to be mapped
     * @return the mapped response
     */
    @Override
    public UserAccessResponse dtoToResponse(UserAccessDTO userAccessDto) {
        return modelMapper.map(userAccessDto, UserAccessResponse.class);
    }

    @Autowired
    public UserAccessDM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapperCustomized modelMapper;
}
