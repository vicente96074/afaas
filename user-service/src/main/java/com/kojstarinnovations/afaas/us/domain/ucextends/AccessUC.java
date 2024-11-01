package com.kojstarinnovations.afaas.us.domain.ucextends;

import com.kojstarinnovations.afaas.commons.data.response.auth.AccessResponse;
import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import com.kojstarinnovations.afaas.commons.ports.input.UseCase;
import com.kojstarinnovations.afaas.us.application.data.request.AccessRequest;

public interface AccessUC extends UseCase<AccessRequest, AccessResponse, Integer> {

    /**
     * getByAccessName
     *
     * @param accessName the access name to search
     * @return access response search result
     */
    AccessResponse getByAccessName(AccessName accessName);

    /**
     * existsByAccessName
     *
     * @param accessName the access name to search
     * @return true if the access exists, false otherwise
     */
    boolean existsByAccessName(AccessName accessName);

    /**
     * existsById
     *
     * @param id the id to search
     * @return true if the access exists, false otherwise
     */
    boolean existsById(int id);
}
