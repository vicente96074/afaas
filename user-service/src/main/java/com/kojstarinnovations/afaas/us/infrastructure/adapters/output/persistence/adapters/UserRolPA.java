package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.adapters;

import com.kojstarinnovations.afaas.us.domain.model.UserRolDTO;
import com.kojstarinnovations.afaas.us.domain.model.UserRolIDDTO;
import com.kojstarinnovations.afaas.us.domain.opextends.UserRolOP;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.UserRolId;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl.UserRolPM;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository.UserRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * UserPA
 *
 * @author Augusto Vicente
 */
@Component
public class UserRolPA implements UserRolOP {

    /**
     * Checks if a user and his/her rol exists in the relational table
     *
     * @param userRolIdDto the user and his/her rol to be checked
     * @return true if the user and his/her rol exists, false otherwise
     */
    @Override
    public boolean existsById(UserRolIDDTO userRolIdDto) {
        UserRolId userRolId = new UserRolId(userRolIdDto.getUserId(), userRolIdDto.getRolId());
        return repository.existsById(userRolId);
    }

    /**
     * Saves a user and his/her rol to the relational table
     *
     * @param modelDto the model to be saved
     * @return the saved model
     */
    @Override
    public UserRolDTO save(UserRolDTO modelDto) {
        return persistenceMapper.entityToDto(
                repository.save(
                        persistenceMapper.dtoToEntity(modelDto)
                )
        );
    }

    /**
     * Get by id
     *
     * @param id the id of the modelDto to be retrieved
     * @return the modelDto retrieved
     */
    @Override
    public Optional<UserRolDTO> getById(UserRolIDDTO id) {
        return repository.findById(new UserRolId(id.getUserId(), id.getRolId()))
                .map(persistenceMapper::entityToDto);
    }

    /**
     * Method to get a page of modelDto
     *
     * @param pageable the pageable object
     * @return Page<DTO>
     */
    @Override
    public Page<UserRolDTO> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get all modelDto
     *
     * @return List<DTO>
     */
    @Override
    public List<UserRolDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Updates a modelDto by id
     *
     * @param modelDto the modelDto to be updated
     * @param id the id of the modelDto to be updated
     * @return the updated modelDto
     */
    @Override
    public UserRolDTO updateById(UserRolDTO modelDto, UserRolIDDTO id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Deletes a modelDto by id
     *
     * @param id the id of the modelDto to be deleted
     */
    @Override
    public void deleteById(UserRolIDDTO id) {
        UserRolId userRolId = new UserRolId(id.getUserId(), id.getRolId());
        repository.deleteById(userRolId);
    }

    /**
     * Deletes a modelDto by id
     *
     * @param userId the id of the modelDto to be deleted
     */
    @Override
    public void deleteByUserId(String userId) {
        repository.deleteByUserId(userId);
    }

    @Autowired
    public UserRolPA(UserRolPM persistenceMapper, UserRolRepository repository) {
        this.persistenceMapper = persistenceMapper;
        this.repository = repository;
    }

    private final UserRolPM persistenceMapper;
    private final UserRolRepository repository;
}