package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.dto.AccessDTO;
import com.kojstarinnovations.afaas.commons.data.response.auth.AccessResponse;
import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import com.kojstarinnovations.afaas.commons.exception.DuplicateException;
import com.kojstarinnovations.afaas.commons.exception.NotFoundException;
import com.kojstarinnovations.afaas.commons.ports.output.EventPublisher;
import com.kojstarinnovations.afaas.commons.ports.output.TransactionId;
import com.kojstarinnovations.afaas.commons.ports.output.event.*;
import com.kojstarinnovations.afaas.us.application.data.request.AccessRequest;
import com.kojstarinnovations.afaas.us.domain.dmimpl.AccessDM;
import com.kojstarinnovations.afaas.us.domain.opextends.AccessOP;
import com.kojstarinnovations.afaas.us.domain.ucextends.AccessUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AccessService implements AccessUC {
    /**
     * Save entity
     *
     * @param accessRequest the entity to save
     * @return QueryResponse the saved entity
     */
    @Override
    public AccessResponse save(AccessRequest accessRequest) {

        if (existsByAccessName(accessRequest.getAccessName())) {
            throw new DuplicateException("Access already exists");
        }

        AccessDTO dto = domainMapper.requestToDto(accessRequest);
        dto.setId(null);
        dto = (AccessDTO) auditAttributeServiceSecurity.getAuditAttributesForSystem(dto); // Add audit attributes for system
        dto = outputPort.save(dto);

        eventPublisher.handle(new EnrollingEvent<>("Access saved", LocalDateTime.now(), "SYSTEM", "AccessService", "save", TransactionId.generateTransactionId(), dto));

        return domainMapper.dtoToResponse(dto);
    }

    /**
     * Delete entity by id
     *
     * @param id the id of the entity to be deleted
     */
    @Override
    public void deleteById(Integer id) {
        outputPort.deleteById(id);

        eventPublisher.handle(new DeletionEvent<>("Access deleted by ID", LocalDateTime.now(), "SYSTEM", "AccessService", "deleteById", TransactionId.generateTransactionId(), id));
    }

    /**
     * Check if entity exists by id
     *
     * @param id id of the object to be retrieved
     * @return boolean
     */
    @Override
    public boolean existsById(Integer id) {
        boolean exists = outputPort.existsById(id);

        eventPublisher.handle(new ExistenceCheckedEvent<>("Existence checked by ID", LocalDateTime.now(), "SYSTEM", "AccessService", "existsById", TransactionId.generateTransactionId(), id));

        return exists;
    }

    /**
     * Get all objects
     *
     * @param ID id of the object to be retrieved
     * @return QueryResponse
     */
    @Override
    public AccessResponse getById(Integer ID) {
        Optional<AccessDTO> optionalAccessDTO = outputPort.getById(ID);

        eventPublisher.handle(new DisplayEvent<>("Access retrieved by ID", LocalDateTime.now(), "SYSTEM", "AccessService", "getById", TransactionId.generateTransactionId(), ID));

        return domainMapper.dtoToResponse(optionalAccessDTO.orElseThrow(() -> new NotFoundException("Access not found by ID")));
    }

    /**
     * Get all objects
     *
     * @param pageable the pageable object
     * @return Page<Response>
     */
    @Override
    public Page<AccessResponse> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Get all objects
     *
     * @return QueryResponse
     */
    @Override
    public List<AccessResponse> getAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Update object by id
     *
     * @param createRequest the object to be updated
     * @param id            id of the object to be updated
     * @return QueryResponse the updated object
     */
    @Override
    public AccessResponse updateById(AccessRequest createRequest, Integer id) {
        if (!existsById(id)) {
            throw new NotFoundException("Access not found by ID");
        }

        AccessDTO modelDto = domainMapper.requestToDto(createRequest);
        modelDto = (AccessDTO) auditAttributeServiceSecurity.getAuditAttributesForUpdate(modelDto); // Add audit attributes for system

        modelDto = outputPort.updateById(modelDto, id);

        eventPublisher.handle(new ModificationEvent<>("Access updated by ID", LocalDateTime.now(), "SYSTEM", "AccessService", "updateById", TransactionId.generateTransactionId(), id));

        return domainMapper.dtoToResponse(modelDto);
    }

    /**
     * getByAccessName
     *
     * @param accessName the access name to search
     * @return access response search result
     */
    @Override
    public AccessResponse getByAccessName(AccessName accessName) {
        Optional<AccessDTO> optionalAccessDTO = outputPort.getByAccessName(accessName);

        eventPublisher.handle(new DisplayEvent<>("Access retrieved by accessName", LocalDateTime.now(), "SYSTEM", "AccessService", "getByAccessName", TransactionId.generateTransactionId(), accessName));

        return domainMapper.dtoToResponse(optionalAccessDTO.orElseThrow(() -> new NotFoundException("Access not found by accessName")));
    }

    /**
     * existsByAccessName
     *
     * @param accessName the access name to search
     * @return true if the access exists, false otherwise
     */
    @Override
    public boolean existsByAccessName(AccessName accessName) {
        boolean exists = outputPort.existsByAccessName(accessName);

        eventPublisher.handle(new ExistenceCheckedEvent<>("Existence checked by accessName", LocalDateTime.now(), "SYSTEM", "AccessService", "existsByAccessName", TransactionId.generateTransactionId(), accessName));

        return exists;
    }

    /**
     * existsById
     *
     * @param id the id to search
     * @return true if the access exists, false otherwise
     */
    @Override
    public boolean existsById(int id) {
        boolean exists = outputPort.existsById(id);

        eventPublisher.handle(new ExistenceCheckedEvent<>("Existence checked by ID", LocalDateTime.now(), "SYSTEM", "AccessService", "existsById", TransactionId.generateTransactionId(), id));

        return exists;
    }

    @Autowired
    public AccessService(
            AuditAttributeServiceSecurity auditAttributeServiceSecurity,
            AccessOP outputPort,
            EventPublisher eventPublisher,
            AccessDM domainMapper
    ) {
        this.auditAttributeServiceSecurity = auditAttributeServiceSecurity;
        this.outputPort = outputPort;
        this.eventPublisher = eventPublisher;
        this.domainMapper = domainMapper;
    }

    private final AuditAttributeServiceSecurity auditAttributeServiceSecurity;
    private final AccessOP outputPort;
    private final EventPublisher eventPublisher;
    private final AccessDM domainMapper;
}