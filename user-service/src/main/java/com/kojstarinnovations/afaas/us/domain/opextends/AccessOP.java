package com.kojstarinnovations.afaas.us.domain.opextends;

import com.kojstarinnovations.afaas.commons.data.dto.AccessDTO;
import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import com.kojstarinnovations.afaas.commons.ports.output.OutputPort;

import java.util.Optional;

public interface AccessOP extends OutputPort<AccessDTO, Integer> {

    /**
     * getByAccessName
     *
     * @param accessName accessName
     * @return Optional<AccessDTO>
     */
    Optional<AccessDTO> getByAccessName(AccessName accessName);

    /**
     * existsByAccessName
     *
     * @param accessName accessName
     * @return boolean
     */
    boolean existsByAccessName(AccessName accessName);

    /**
     * existsById
     *
     * @param id id
     * @return boolean
     */
    boolean existsById(int id);
}
