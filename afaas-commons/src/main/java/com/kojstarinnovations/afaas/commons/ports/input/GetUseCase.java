package com.kojstarinnovations.afaas.commons.ports.input;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Generic get use case
 *
 * @author Augusto Vicente
 * @param <Response>
 * @param <ID>
 */
public interface GetUseCase<Response, ID> {

    /**
     * Get all objects
     *
     * @param id id of the object to be retrieved
     * @return QueryResponse
     */
    Response getById(ID id);

    /**
     * Get all objects
     *
     * @param pageable the pageable object
     * @return QueryResponse
     */
    Page<Response> getPage(Pageable pageable);

    /**
     * Get all objects
     *
     * @return QueryResponse
     */
    List<Response> getAll();
}
