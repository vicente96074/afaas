package com.kojstarinnovations.afaas.commons.ports.input;

/**
 * Generic save use case
 *
 * @author Augusto Vicente
 * @param <Request> Create request type
 * @param <Response> Query response type
 */
public interface CreateUseCase<Request, Response> {


    /**
     * Save a request
     *
     * @param request the entity to save
     * @return QueryResponse the saved entity
     */
    Response save(Request request);
}
