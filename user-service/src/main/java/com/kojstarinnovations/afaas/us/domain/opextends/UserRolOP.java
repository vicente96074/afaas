package com.kojstarinnovations.afaas.us.domain.opextends;

import com.kojstarinnovations.afaas.commons.ports.output.OutputPort;
import com.kojstarinnovations.afaas.us.domain.model.UserRolDTO;
import com.kojstarinnovations.afaas.us.domain.model.UserRolIDDTO;

/**
 * UserRolOP
 *
 * @author Augusto Vicente
 */
public interface UserRolOP extends OutputPort<UserRolDTO, UserRolIDDTO> {

    /**
     * existsById method
     *
     * @param userRolIdDto userRolIdDto to search
     * @return true if the userRol exists, false otherwise
     */
    boolean existsById(UserRolIDDTO userRolIdDto);

    /**
     * deleteByUserId method
     *
     * @param userId the userId to be deleted
     */
    void deleteByUserId(String userId);
}
