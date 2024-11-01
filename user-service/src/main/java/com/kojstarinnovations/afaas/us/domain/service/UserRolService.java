package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.response.auth.UserRolResponse;
import com.kojstarinnovations.afaas.commons.exception.DuplicateException;
import com.kojstarinnovations.afaas.commons.exception.NotFoundException;
import com.kojstarinnovations.afaas.commons.ports.output.EventPublisher;
import com.kojstarinnovations.afaas.commons.ports.output.TransactionId;
import com.kojstarinnovations.afaas.commons.ports.output.event.*;
import com.kojstarinnovations.afaas.us.application.data.request.UserRolIdRequest;
import com.kojstarinnovations.afaas.us.application.data.request.UserRolRequest;
import com.kojstarinnovations.afaas.us.domain.dmimpl.UserRolDM;
import com.kojstarinnovations.afaas.us.domain.model.UserRolDTO;
import com.kojstarinnovations.afaas.us.domain.model.UserRolIDDTO;
import com.kojstarinnovations.afaas.us.domain.opextends.UserRolOP;
import com.kojstarinnovations.afaas.us.domain.ucextends.UserRolUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * UserRolService
 *
 * @author Augusto Vicente
 */
@Service
@Transactional
public class UserRolService implements UserRolUC {

    /**
     * existsById method
     *
     * @param userRolIdRequest userRolIdRequest to search
     * @return true if the userRol exists, false otherwise
     */
    @Override
    public boolean existsById(UserRolIdRequest userRolIdRequest) {
        UserRolIDDTO userRolIdDto = new UserRolIDDTO(userRolIdRequest.getUserId(), userRolIdRequest.getRolId());
        boolean exists = outputPort.existsById(userRolIdDto);

        eventPublisher.handle(new ExistenceCheckedEvent<>("User rol checked by id composed", LocalDateTime.now(), "SYSTEM", "UserRolService", "existsById", TransactionId.generateTransactionId(), userRolIdRequest));

        return exists;
    }

    /**
     * save method
     *
     * @param request request to save
     * @return the saved request
     */
    @Override
    public UserRolResponse save(UserRolRequest request) {
        if (existsById(new UserRolIdRequest(request.getUserId(), request.getRolId()))) {
            throw new DuplicateException("UserRol already exists");
        }
        UserRolDTO dto = domainMapper.requestToDto(request);
        dto = (UserRolDTO) auditAttributeServiceSecurity.getAuditAttributesForSystem(dto); // Add audit attributes for system
        dto = outputPort.save(dto);

        eventPublisher.handle(new EnrollingEvent<>("User rol enrolled", LocalDateTime.now(), "SYSTEM", "UserRolService", "save", TransactionId.generateTransactionId(), new UserRolIdRequest(request.getUserId(), request.getRolId())));
        return domainMapper.dtoToResponse(dto);
    }

    /**
     * deleteById method
     *
     * @param id id to delete
     */
    @Override
    public void deleteById(UserRolIdRequest id) {
        UserRolIDDTO userRolIdDto = new UserRolIDDTO(id.getUserId(), id.getRolId());
        outputPort.deleteById(userRolIdDto);

        eventPublisher.handle(new DeletionEvent<>("User rol deleted by id composed", LocalDateTime.now(), "SYSTEM", "UserRolService", "deleteById", TransactionId.generateTransactionId(), id));
    }

    /**
     * deleteByUserId method
     *
     * @param userId userId to delete
     */
    @Override
    public void deleteByUserId(String userId) {
        outputPort.deleteByUserId(userId);

        eventPublisher.handle(new DeletionEvent<>("User rol deleted by user id", LocalDateTime.now(), "SYSTEM", "UserRolService", "deleteByUserId", TransactionId.generateTransactionId(), userId));
    }

    /**
     * getById method
     *
     * @param ID id to search
     * @return the userRol retrieved
     */
    @Override
    public UserRolResponse getById(UserRolIdRequest ID) {
        UserRolIDDTO userRolIdDto = new UserRolIDDTO(ID.getUserId(), ID.getRolId());
        Optional<UserRolDTO> optionalUserRolDto = outputPort.getById(userRolIdDto);

        eventPublisher.handle(new DisplayEvent<>("User rol displayed by id composed", LocalDateTime.now(), "SYSTEM", "UserRolService", "getById", TransactionId.generateTransactionId(), ID));

        return domainMapper.dtoToResponse(optionalUserRolDto.orElseThrow(() -> new NotFoundException("UserRol not found")));
    }

    /**
     * Get all objects
     *
     * @param pageable the pageable object
     * @return Page<Response>
     */
    @Override
    public Page<UserRolResponse> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get all objects
     *
     * @return QueryResponse
     */
    @Override
    public List<UserRolResponse> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * updateById method
     *
     * @param request request to update
     * @param id      id to update
     * @return the updated userRol
     */
    @Override
    public UserRolResponse updateById(UserRolRequest request, UserRolIdRequest id) {

        if (!existsById(id)) {
            throw new NotFoundException("UserRol not found");
        }

        UserRolDTO modelDTO = domainMapper.requestToDto(request);
        modelDTO = (UserRolDTO) auditAttributeServiceSecurity.getAuditAttributesForUpdate(modelDTO);

        modelDTO = outputPort.updateById(modelDTO, new UserRolIDDTO(id.getUserId(), id.getRolId()));

        eventPublisher.handle(new ModificationEvent<>("User rol modified by id composed", LocalDateTime.now(), "SYSTEM", "UserRolService", "updateById", TransactionId.generateTransactionId(), id));

        return domainMapper.dtoToResponse(modelDTO);
    }

    @Autowired
    public UserRolService(
            AuditAttributeServiceSecurity auditAttributeServiceSecurity,
            UserRolOP outputPort,
            EventPublisher eventPublisher,
            UserRolDM domainMapper
    ) {
        this.auditAttributeServiceSecurity = auditAttributeServiceSecurity;
        this.outputPort = outputPort;
        this.eventPublisher = eventPublisher;
        this.domainMapper = domainMapper;
    }

    private final AuditAttributeServiceSecurity auditAttributeServiceSecurity;
    private final UserRolOP outputPort;
    private final EventPublisher eventPublisher;
    private final UserRolDM domainMapper;
}