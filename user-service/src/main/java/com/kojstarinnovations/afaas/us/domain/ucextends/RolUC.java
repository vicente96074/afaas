package com.kojstarinnovations.afaas.us.domain.ucextends;

import com.kojstarinnovations.afaas.commons.data.response.auth.RolResponse;
import com.kojstarinnovations.afaas.commons.emuns.RolName;
import com.kojstarinnovations.afaas.commons.ports.input.UseCase;
import com.kojstarinnovations.afaas.us.application.data.request.RolRequest;

/**
 *
 * RolUC
 *
 * @author BalamKiche
 *
 */
public interface RolUC extends UseCase<RolRequest, RolResponse, Integer> {

    /**
     * getByRolName
     *
     * @param rolName the rol name to search
     * @return rol response search result
     */
    RolResponse getByRolName(RolName rolName);

    /**
     * existsByRolName
     *
     * @param rolName the rol name to search
     * @return true if the rol exists, false otherwise
     */
    boolean existsByRolName(RolName rolName);

    /**
     * existsById
     *
     * @param id the id to search
     * @return true if the rol exists, false otherwise
     */
    boolean existsById(int id);
}
