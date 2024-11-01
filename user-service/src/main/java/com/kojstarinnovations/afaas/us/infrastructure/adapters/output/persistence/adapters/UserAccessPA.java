package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.adapters;

import com.kojstarinnovations.afaas.us.domain.model.UserAccessDTO;
import com.kojstarinnovations.afaas.us.domain.opextends.UserAccessOP;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl.UserAccessPM;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository.UserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserAccessPA implements UserAccessOP {

    /**
     * Method to save a modelDto
     *
     * @param modelDto the modelDto to be saved
     * @return modelDto
     */
    @Override
    public UserAccessDTO save(UserAccessDTO modelDto) {
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
    public Optional<UserAccessDTO> getById(Integer id) {
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
    public Page<UserAccessDTO> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get all modelDto
     *
     * @return List<DTO>
     */
    @Override
    public List<UserAccessDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to update a modelDto by id
     *
     * @param modelDto the modelDto to be updated
     * @param id  the id of the modelDto to be updated
     * @return modelDto updated
     */
    @Override
    public UserAccessDTO updateById(UserAccessDTO modelDto, Integer id) {
        throw new UnsupportedOperationException("Not implemented yet");
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
     * existsById method
     *
     * @param id id to search
     * @return true if the userAccess exists, false otherwise
     */
    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    /**
     * deleteByUserId method
     *
     * @param userId the userId to be deleted
     */
    @Override
    public void deleteByUserId(String userId) {
        repository.deleteByUserId(userId);
    }

    /**
     * existsByUserIdAndAccessId method
     *
     * @param userId   the userId to be checked
     * @param accessId the accessId to be checked
     * @return true if the user access exists, false otherwise
     */
    @Override
    public boolean existsByUserIdAndAccessId(String userId, int accessId) {
        return repository.existsByUserIdAndAccessId(userId, accessId);
    }

    @Autowired
    public UserAccessPA(UserAccessRepository repository, UserAccessPM persistenceMapper) {
        this.repository = repository;
        this.persistenceMapper = persistenceMapper;
    }

    private final UserAccessRepository repository;
    private final UserAccessPM persistenceMapper;
}