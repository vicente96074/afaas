package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.adapters;

import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;
import com.kojstarinnovations.afaas.commons.emuns.Status;
import com.kojstarinnovations.afaas.us.domain.opextends.UserOP;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.User;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.pmimpl.UserPM;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * UserPA
 *
 * @author Augusto Vicente
 */
@Component
public class UserPA implements UserOP {

    /**
     * This method validates if a user exists by id
     *
     * @param s the id of the user to be validated
     * @return boolean true if the user exists, false otherwise
     */
    @Override
    public boolean existsById(String s) {
        return repository.existsById(s);
    }

    /**
     * This method saves a user
     *
     * @param dto the user to be saved
     * @return UserDTO the saved user
     */
    @Override
    public UserDTO save(UserDTO dto) {
        return persistenceMapper.entityToDtoWithAccessAndRoles(
                repository.save(
                        persistenceMapper.dtoToEntity(dto)
                )
        );
    }

    /**
     * This method gets a user by id
     *
     * @param s the id of the user to be retrieved
     * @return Optional<UserDTO> the user retrieved
     */
    @Override
    public Optional<UserDTO> getById(String s) {
        return repository.findById(s)
                .map(persistenceMapper::entityToDtoWithAccessAndRoles);
    }

    /**
     * Method to get a page of modelDto
     *
     * @param pageable the pageable object
     * @return Page<DTO>
     */
    @Override
    public Page<UserDTO> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get all modelDto
     *
     * @return List<DTO>
     */
    @Override
    public List<UserDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This method updates a user
     *
     * @param modelDto the user to be updated
     * @param id       the id of the user to be updated
     * @return UserDTO the updated user
     */
    @Override
    public UserDTO updateById(UserDTO modelDto, String id) {
        User user = persistenceMapper.dtoToEntity(modelDto);
        user.setId(id);

        return persistenceMapper.entityToDto(repository.save(user));
    }

    /**
     * This method deletes a user by id
     *
     * @param s the id of the user to be deleted
     */
    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }

    /**
     * This method gets a user by username
     *
     * @param username the username of the user to be retrieved
     * @return Optional<UserDTO> the user retrieved
     */
    @Override
    public Optional<UserDTO> getByUsername(String username) {
        return repository.findByUsername(username)
                .map(persistenceMapper::entityToDtoWithAccessAndRoles);
    }

    /**
     * This method validates if a user exists by username
     *
     * @param username the username of the user to be validated
     * @return boolean true if the user exists, false otherwise
     */
    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    /**
     * This method validates if a user exists by email
     *
     * @param email the email of the user to be validated
     * @return boolean true if the user exists, false otherwise
     */
    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    /**
     * existsByPhone
     *
     * @param phone phone
     * @return boolean
     */
    @Override
    public boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }

    /**
     * This method validates if a user exists by person identification and person identification type
     *
     * @param identification the identification of the user to be validated
     * @return boolean true if the user exists, false otherwise
     */
    @Override
    public boolean existsByIdentification(String identification) {
        return repository.existsByIdentification(identification);
    }

    /**
     * This method gets a page of active users by store id
     *
     * @param pageable the pageable object
     * @return Page<UserDTO> the page of active users
     */
    @Override
    public Page<UserDTO> getPageUserActive(Pageable pageable) {
        return repository.getFindByStatus(Status.ACTIVE, pageable)
                .map(persistenceMapper::entityToDtoWithAccessAndRoles);
    }

    /**
     * This method gets a page of active users
     *
     * @param pageable the pageable object
     * @return Page<UserDTO> the page of active users
     */
    @Override
    public Page<UserDTO> getPageBSUserActive(String storeBranchId, Pageable pageable) {
        return repository.findActiveByBSAndStatus(Status.ACTIVE, storeBranchId, pageable)
                .map(persistenceMapper::entityToDtoWithAccessAndRoles);
    }

    /**
     * This method gets a page of active users
     *
     * @param pageable the pageable object
     * @return Page<UserDTO> the page of active users
     */
    @Override
    public Page<UserDTO> getPageSTUserActive(String storeId, Pageable pageable) {
        return repository.findActiveBySTAndStatus(Status.ACTIVE, storeId, pageable)
                .map(persistenceMapper::entityToDtoWithAccessAndRoles);
    }

    /**
     * This method updates the store branch id of a user
     *
     * @param id            the id of the user to be updated
     * @param storeBranchId the store branch id to be updated
     */
    @Override
    public void updateStoreBranchId(String id, String storeBranchId) {
        repository.updateStoreBranchId(id, storeBranchId);
    }

    /**
     * updateStoreBranchId
     *
     * @param id      the id
     * @param storeId the store id
     */
    @Override
    public void updateStoreId(String id, String storeId) {
        repository.updateStoreId(id, storeId);
    }

    /**
     * This method updates the profile picture of a user
     *
     * @param id             the id of the user to be updated
     * @param profilePicture the profile picture to be updated
     * @param updatedAt      the date of the update
     * @param updatedBy      the user who updated
     */
    @Override
    public void updateProfilePicture(String id, String profilePicture, LocalDateTime updatedAt, String updatedBy) {
        repository.updateProfilePicture(id, profilePicture, updatedAt, updatedBy);
    }

    @Autowired
    public UserPA(UserPM persistenceMapper, UserRepository repository) {
        this.persistenceMapper = persistenceMapper;
        this.repository = repository;
    }

    private final UserPM persistenceMapper;
    private final UserRepository repository;
}