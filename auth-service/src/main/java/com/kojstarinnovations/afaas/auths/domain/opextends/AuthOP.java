package com.kojstarinnovations.afaas.auths.domain.opextends;

import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;

import java.util.Optional;

/**
 * UserOP
 *
 * @author BalamKiche
 */
public interface AuthOP {

    /**
     * getByUsername
     *
     * @param username username
     * @return Optional<UserDTO>
     */
    Optional<UserDTO> getByUsername(String username);

    boolean existsByUsername(String username);
}
