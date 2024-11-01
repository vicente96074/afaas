package com.kojstarinnovations.afaas.us.domain.ucextends;

import com.kojstarinnovations.afaas.commons.data.response.auth.UserAccessResponse;
import com.kojstarinnovations.afaas.commons.ports.input.UseCase;
import com.kojstarinnovations.afaas.us.application.data.request.UserAccessRequest;

public interface UserAccessUC extends UseCase<UserAccessRequest, UserAccessResponse, Integer> {


        void deleteByUserId(String userId);

        boolean existsByUserIdAndAccessId(String userId, int accessId);
}
