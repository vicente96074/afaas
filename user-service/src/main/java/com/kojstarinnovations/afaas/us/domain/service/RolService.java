package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.dto.RolDTO;
import com.kojstarinnovations.afaas.commons.data.response.auth.RolResponse;
import com.kojstarinnovations.afaas.commons.emuns.RolName;
import com.kojstarinnovations.afaas.commons.exception.DuplicateException;
import com.kojstarinnovations.afaas.commons.exception.NotFoundException;
import com.kojstarinnovations.afaas.commons.ports.output.EventPublisher;
import com.kojstarinnovations.afaas.commons.ports.output.TransactionId;
import com.kojstarinnovations.afaas.commons.ports.output.event.*;
import com.kojstarinnovations.afaas.us.application.data.request.RolRequest;
import com.kojstarinnovations.afaas.us.domain.dmimpl.RolDM;
import com.kojstarinnovations.afaas.us.domain.opextends.RolOP;
import com.kojstarinnovations.afaas.us.domain.ucextends.RolUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * RolService
 *
 * @author BalamKiche
 */
@Transactional
@Service
public class RolService implements RolUC {

    @Override
    public boolean existsById(int id) {
        boolean exists =  outputPort.existsById(id);

        eventPublisher.handle(new ExistenceCheckedEvent<>("Existence checked by ID", LocalDateTime.now(), "SYSTEM", "RolService", "existsById", TransactionId.generateTransactionId(), id));

        return exists;
    }

    /**
     * existsByRolName method
     *
     * @param rolName rolName to search
     * @return true if the rol exists, false otherwise
     */
    @Override
    public boolean existsByRolName(RolName rolName) {
        boolean exists =  outputPort.existsByRolName(rolName);

        eventPublisher.handle(new ExistenceCheckedEvent<>("Existence checked by rolName", LocalDateTime.now(), "SYSTEM", "RolService", "existsByRolName", TransactionId.generateTransactionId(), rolName));

        return exists;
    }

    /**
     * save method
     *
     * @param request request to save
     * @return the saved request
     */
    @Override
    public RolResponse save(RolRequest request) {

        if (existsByRolName(request.getRolName())) {
            throw new DuplicateException("Rol already exists");
        }

        RolDTO dto = domainMapper.requestToDto(request);
        dto.setId(null);
        dto = (RolDTO) auditAttributeServiceSecurity.getAuditAttributesForSystem(dto); // Add audit attributes for system

        dto = outputPort.save(dto);

        eventPublisher.handle(new EnrollingEvent<>("Rol created", LocalDateTime.now(), "SYSTEM", "RolService", "save", TransactionId.generateTransactionId(), dto.getId()));

        return domainMapper.dtoToResponse(dto);
    }

    /**
     * deleteById method
     *
     * @param integer id of the object to be deleted
     */
    @Override
    public void deleteById(Integer integer) {
        outputPort.deleteById(integer);

        eventPublisher.handle(new DeletionEvent<>("Rol deleted by ID", LocalDateTime.now(), "SYSTEM", "RolService", "deleteById", TransactionId.generateTransactionId(), integer));
    }

    /**
     * getById method
     *
     * @param ID id of the object to be retrieved
     * @return the request found
     */
    @Override
    public RolResponse getById(Integer ID) {
        Optional<RolDTO> optionalRolDto = outputPort.getById(ID);

        eventPublisher.handle(new DisplayEvent<>("Rol retrieved by ID", LocalDateTime.now(), "SYSTEM", "RolService", "getById", TransactionId.generateTransactionId(), ID));

        return domainMapper.dtoToResponse(optionalRolDto.orElseThrow(() -> new NotFoundException("Rol not found by ID")));
    }

    /**
     * Get all objects
     *
     * @param pageable the pageable object
     * @return Page<Response>
     */
    @Override
    public Page<RolResponse> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get all objects
     *
     * @return QueryResponse
     */
    @Override
    public List<RolResponse> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * updateById method
     *
     * @param request request to be updated
     * @param id id of the object to be updated
     * @return the updated request
     */
    @Override
    public RolResponse updateById(RolRequest request, Integer id) {

        if (!existsById(id)) {
            throw new NotFoundException("Rol not found by ID");
        }

        RolDTO modelDto = domainMapper.requestToDto(request);
        modelDto = (RolDTO) auditAttributeServiceSecurity.getAuditAttributesForUpdate(modelDto); // Add audit attributes for system

        modelDto = outputPort.updateById(modelDto, id);

        eventPublisher.handle(new ModificationEvent<>("Rol updated by ID", LocalDateTime.now(), "SYSTEM", "RolService", "updateById", TransactionId.generateTransactionId(), id));

        return domainMapper.dtoToResponse(modelDto);
    }

    /**
     * getByRolName method
     *
     * @param rolName rolName to search
     * @return the request found
     */
    @Override
    public RolResponse getByRolName(RolName rolName) {
        Optional<RolDTO> optionalRolDto = outputPort.getByRolName(rolName);

        eventPublisher.handle(new DisplayEvent<>("Rol retrieved by rolName", LocalDateTime.now(), "SYSTEM", "RolService", "getByRolName", TransactionId.generateTransactionId(), rolName));

        return domainMapper.dtoToResponse(optionalRolDto.orElseThrow(() -> new NotFoundException("Rol not found by rolName")));
    }

    /**
     * Check if entity exists by id
     *
     * @param integer id of the object to be retrieved
     * @return boolean
     */
    @Override
    public boolean existsById(Integer integer) {
        boolean exists = outputPort.existsById(integer);

        eventPublisher.handle(new ExistenceCheckedEvent<>("Existence checked by ID", LocalDateTime.now(), "SYSTEM", "RolService", "existsById", TransactionId.generateTransactionId(), integer));

        return exists;
    }

    @Autowired
    public RolService(
            AuditAttributeServiceSecurity auditAttributeServiceSecurity,
            RolOP outputPort,
            EventPublisher eventPublisher,
            RolDM domainMapper
    ) {
        this.auditAttributeServiceSecurity = auditAttributeServiceSecurity;
        this.outputPort = outputPort;
        this.eventPublisher = eventPublisher;
        this.domainMapper = domainMapper;
    }
    private final AuditAttributeServiceSecurity auditAttributeServiceSecurity;
    private final RolOP outputPort;
    private final EventPublisher eventPublisher;
    private final RolDM domainMapper;
}