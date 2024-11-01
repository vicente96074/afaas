package com.kojstarinnovations.afaas.us.domain.opextends;

import com.kojstarinnovations.afaas.commons.ports.output.OutputPort;
import com.kojstarinnovations.afaas.us.domain.model.UserAccessDTO;

public interface UserAccessOP extends OutputPort<UserAccessDTO, Integer> {

    /**
     * deleteByUserId method
     *
     * @param userId the userId to be deleted
     */
    void deleteByUserId(String userId);

    /**
     * existsByUserIdAndAccessId method
     *
     * @param userId the userId to be checked
     * @param accessId the accessId to be checked
     * @return true if the user access exists, false otherwise
     */
    boolean existsByUserIdAndAccessId(String userId, int accessId);
}
