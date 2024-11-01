package com.kojstarinnovations.afaas.us.domain.opextends;

import com.kojstarinnovations.afaas.commons.data.dto.RolDTO;
import com.kojstarinnovations.afaas.commons.emuns.RolName;
import com.kojstarinnovations.afaas.commons.ports.output.OutputPort;

import java.util.Optional;

/**
 * RolOP
 *
 * @author BalamKiche
 */
public interface RolOP extends OutputPort<RolDTO, Integer> {

    /**
     * getByRolName
     *
     * @param rolName rolName
     * @return Optional<RolDTO>
     */
    Optional<RolDTO> getByRolName(RolName rolName);

    /**
     * existsByRolName
     *
     * @param rolName rolName
     * @return boolean
     */
    boolean existsByRolName(RolName rolName);

    /**
     * existsById
     *
     * @param id id
     * @return boolean
     */
    boolean existsById(int id);
}