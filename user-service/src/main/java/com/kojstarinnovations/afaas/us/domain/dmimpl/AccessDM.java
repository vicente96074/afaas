package com.kojstarinnovations.afaas.us.domain.dmimpl;

import com.kojstarinnovations.afaas.commons.data.dto.AccessDTO;
import com.kojstarinnovations.afaas.commons.data.response.auth.AccessResponse;
import com.kojstarinnovations.afaas.commons.dm.DomainMapper;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import com.kojstarinnovations.afaas.us.application.data.request.AccessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessDM implements DomainMapper<AccessDTO, AccessRequest, AccessResponse> {
    @Override
    public AccessDTO requestToDto(AccessRequest accessRequest) {
        return modelMapper.map(accessRequest, AccessDTO.class);
    }

    @Override
    public AccessResponse dtoToResponse(AccessDTO accessDTO) {
        return modelMapper.map(accessDTO, AccessResponse.class);
    }

    @Autowired
    public AccessDM(ModelMapperCustomized modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapperCustomized modelMapper;
}