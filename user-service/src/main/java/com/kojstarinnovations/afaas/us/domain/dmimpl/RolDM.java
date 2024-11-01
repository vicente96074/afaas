package com.kojstarinnovations.afaas.us.domain.dmimpl;

import com.kojstarinnovations.afaas.commons.data.dto.RolDTO;
import com.kojstarinnovations.afaas.commons.data.response.auth.RolResponse;
import com.kojstarinnovations.afaas.commons.dm.DomainMapper;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.us.application.data.request.RolRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RolDM maps a rol request to a rol dto and a rol dto to a rol response
 *
 * @author Augusto Vicente
 */
@Component
public class RolDM implements DomainMapper<RolDTO, RolRequest, RolResponse> {

    /**
     * Maps a rol request to a rol dto
     *
     * @param rolRequest the request to be mapped
     * @return the mapped dto
     */
    @Override
    public RolDTO requestToDto(RolRequest rolRequest) {
        return modelMapper.map(rolRequest, RolDTO.class);
    }

    /**
     * Maps a rol dto to a rol response
     *
     * @param rolDto the dto to be mapped
     * @return the mapped response
     */
    @Override
    public RolResponse dtoToResponse(RolDTO rolDto) {
        return modelMapper.map(rolDto, RolResponse.class);
    }

    /**
     * Maps a rol response to a rol dto
     *
     * @param rolResponse the response to be mapped
     * @return the mapped dto
     */
    public RolDTO responseToDto(RolResponse rolResponse) {
        return modelMapper.map(rolResponse, RolDTO.class);
    }

    @Autowired
    public RolDM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapperCustomized modelMapper;
}
