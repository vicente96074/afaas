package com.kojstarinnovations.afaas.us.domain.ucextends;

import com.kojstarinnovations.afaas.commons.data.response.auth.UserRolResponse;
import com.kojstarinnovations.afaas.commons.ports.input.UseCase;
import com.kojstarinnovations.afaas.us.application.data.request.UserRolIdRequest;
import com.kojstarinnovations.afaas.us.application.data.request.UserRolRequest;

/**
 * UserRolUC
 *
 * @author Augusto Vicente
 */
public interface UserRolUC extends UseCase<UserRolRequest, UserRolResponse, UserRolIdRequest> {

    /**
     * Checks if a user and his/her rol exists in the relational table
     *
     * @param userRolIdRequest the user and his/her rol to be checked
     * @return true if the user and his/her rol exists, false otherwise
     */
    boolean existsById(UserRolIdRequest userRolIdRequest);

    void deleteByUserId(String userId);
}
