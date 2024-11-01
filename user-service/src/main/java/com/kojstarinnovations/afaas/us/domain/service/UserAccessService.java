package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.response.auth.UserAccessResponse;
import com.kojstarinnovations.afaas.commons.exception.DuplicateException;
import com.kojstarinnovations.afaas.commons.exception.NotFoundException;
import com.kojstarinnovations.afaas.commons.ports.output.EventPublisher;
import com.kojstarinnovations.afaas.commons.ports.output.TransactionId;
import com.kojstarinnovations.afaas.commons.ports.output.event.*;
import com.kojstarinnovations.afaas.us.application.data.request.UserAccessRequest;
import com.kojstarinnovations.afaas.us.domain.dmimpl.UserAccessDM;
import com.kojstarinnovations.afaas.us.domain.model.UserAccessDTO;
import com.kojstarinnovations.afaas.us.domain.opextends.UserAccessOP;
import com.kojstarinnovations.afaas.us.domain.ucextends.UserAccessUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserAccessService implements UserAccessUC {

    /**
     * Save entity
     *
     * @param request the entity to save
     * @return QueryResponse the saved entity
     */
    @Override
    public UserAccessResponse save(UserAccessRequest request) {
        if (existsById(request.getId())) {
            throw new DuplicateException("User access already exists");
        }

        UserAccessDTO dto = domainMapper.requestToDto(request);
        dto = (UserAccessDTO) auditAttributeServiceSecurity.getAuditAttributesForSystem(dto); // Add audit attributes for system

        dto = outputPort.save(dto);

        eventPublisher.handle(new EnrollingEvent<>("User access enrolled", LocalDateTime.now(), "SYSTEM", "UserAccessService", "save", TransactionId.generateTransactionId(), dto));

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

        eventPublisher.handle(new DeletionEvent<>("User access deleted by ID", LocalDateTime.now(), "SYSTEM", "UserAccessService", "deleteById", TransactionId.generateTransactionId(), id));
    }

    @Override
    public boolean existsByUserIdAndAccessId(String userId, int accessId) {
        boolean exists = outputPort.existsByUserIdAndAccessId(userId, accessId);

        eventPublisher.handle(new ExistenceCheckedEvent<>("User access checked by user id and access id", LocalDateTime.now(), "SYSTEM", "UserAccessService", "existsByUserIdAndAccessId", TransactionId.generateTransactionId(), userId + accessId));

        return exists;
    }

    @Override
    public UserAccessResponse getById(Integer ID) {
        Optional<UserAccessDTO> userAccessDto = outputPort.getById(ID);

        eventPublisher.handle(new DisplayEvent<>("User access displayed by id composed", LocalDateTime.now(), "SYSTEM", "UserAccessService", "getById", TransactionId.generateTransactionId(), ID));

        return domainMapper.dtoToResponse(userAccessDto.orElseThrow(() -> new NotFoundException("User access not found")));
    }

    /**
     * Get all objects
     *
     * @param pageable the pageable object
     * @return Page<Response>
     */
    @Override
    public Page<UserAccessResponse> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get all objects
     *
     * @return QueryResponse
     */
    @Override
    public List<UserAccessResponse> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Update object by id
     *
     * @param createRequest the object to be updated
     * @param id            id of the object to be updated
     * @return QueryResponse the updated object
     */
    @Override
    public UserAccessResponse updateById(UserAccessRequest createRequest, Integer id) {
        if (!existsById(id)) {
            throw new NotFoundException("User access not found");
        }

        UserAccessDTO modelDTO = domainMapper.requestToDto(createRequest);
        modelDTO = (UserAccessDTO) auditAttributeServiceSecurity.getAuditAttributesForUpdate(modelDTO); // Add audit attributes for system

        modelDTO = outputPort.save(modelDTO);

        eventPublisher.handle(new ModificationEvent<>("User access modified by id composed", LocalDateTime.now(), "SYSTEM", "UserAccessService", "updateById", TransactionId.generateTransactionId(), id));

        return domainMapper.dtoToResponse(modelDTO);
    }

    /**
     * Delete entity by user id
     *
     * @param userId the id of the user to be deleted
     */
    @Override
    public void deleteByUserId(String userId) {
        outputPort.deleteByUserId(userId);

        eventPublisher.handle(new DeletionEvent<>("User access deleted by ID", LocalDateTime.now(), "SYSTEM", "UserAccessService", "deleteByUserId", TransactionId.generateTransactionId(), userId));
    }

    /**
     * Check if entity exists by id
     *
     * @param id id of the object to be retrieved
     * @return boolean
     */
    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Autowired
    public UserAccessService(
            AuditAttributeServiceSecurity auditAttributeServiceSecurity,
            UserAccessOP outputPort,
            EventPublisher eventPublisher,
            UserAccessDM domainMapper
    ) {
        this.auditAttributeServiceSecurity = auditAttributeServiceSecurity;
        this.outputPort = outputPort;
        this.eventPublisher = eventPublisher;
        this.domainMapper = domainMapper;
    }

    private final AuditAttributeServiceSecurity auditAttributeServiceSecurity;
    private final UserAccessOP outputPort;
    private final EventPublisher eventPublisher;
    private final UserAccessDM domainMapper;
}