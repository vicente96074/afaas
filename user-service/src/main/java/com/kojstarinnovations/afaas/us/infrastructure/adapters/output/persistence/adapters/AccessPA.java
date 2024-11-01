package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.adapters;

import com.kojstarinnovations.afaas.commons.data.dto.AccessDTO;
import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import com.kojstarinnovations.afaas.us.domain.opextends.AccessOP;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.Access;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl.AccessPM;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccessPA implements AccessOP {
    /**
     * Method to save a modelDto
     *
     * @param modelDto the modelDto to be saved
     * @return modelDto
     */
    @Override
    public AccessDTO save(AccessDTO modelDto) {
        return persistenceMapper.entityToDto(
                repository.save(
                        persistenceMapper.dtoToEntity(modelDto)
                )
        );
    }

    /**
     * Method to get a modelDto by id
     *
     * @param id the id of the modelDto to be retrieved
     * @return modelDto with the given id
     */
    @Override
    public Optional<AccessDTO> getById(Integer id) {
        return repository.findById(id)
                .map(persistenceMapper::entityToDto);
    }

    /**
     * Method to get a page of modelDto
     *
     * @param pageable the pageable object
     * @return Page<DTO>
     */
    @Override
    public Page<AccessDTO> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Method to get all modelDto
     *
     * @return List<DTO>
     */
    @Override
    public List<AccessDTO> getAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Method to update a modelDto by id
     *
     * @param modelDto the modelDto to be updated
     * @param id  the id of the modelDto to be updated
     * @return modelDto updated
     */
    @Override
    public AccessDTO updateById(AccessDTO modelDto, Integer id) {
        Access access = persistenceMapper.dtoToEntity(modelDto);
        access.setId(id);

        return persistenceMapper.entityToDto(repository.save(access));
    }

    /**
     * Method to delete a modelDto by id
     *
     * @param id the id of the modelDto to be deleted
     */
    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Method to check if a modelDto exists by id
     *
     * @param id the id of the modelDto to be checked
     * @return true if the modelDto exists, false otherwise
     */
    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    /**
     * getByAccessName
     *
     * @param accessName accessName
     * @return Optional<AccessDTO>
     */
    @Override
    public Optional<AccessDTO> getByAccessName(AccessName accessName) {
        return repository.findByAccessName(accessName)
                .map(persistenceMapper::entityToDto);
    }

    /**
     * existsByAccessName
     *
     * @param accessName accessName
     * @return boolean
     */
    @Override
    public boolean existsByAccessName(AccessName accessName) {
        return repository.existsByAccessName(accessName);
    }

    /**
     * existsById
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean existsById(int id) {
        return repository.existsById(id);
    }

    @Autowired
    public AccessPA(AccessRepository repository, AccessPM persistenceMapper) {
        this.repository = repository;
        this.persistenceMapper = persistenceMapper;
    }

    private final AccessRepository repository;
    private final AccessPM persistenceMapper;
}