package com.kojstarinnovations.afaas.us.domain.opextends;

import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;
import com.kojstarinnovations.afaas.commons.ports.output.OutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * UserOP
 *
 * @author BalamKiche
 */
public interface UserOP extends OutputPort<UserDTO, String> {

    /**
     * getByUsername
     *
     * @param username username
     * @return Optional<UserDTO>
     */
    Optional<UserDTO> getByUsername(String username);

    /**
     * existsByUsername
     *
     * @param username username
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     * existsByEmail
     *
     * @param email email
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * existsByPhone
     *
     * @param phone phone
     * @return boolean
     */
    boolean existsByPhone(String phone);

    /**
     * existsByPersonIdentificationAndPersonIdentificationType
     *
     * @param identification personIdentification
     * @return boolean
     */
    boolean existsByIdentification(String identification);

    /**
     * getPageActiveByStoreId
     *
     * @param pageable pageable
     * @return Page<UserDTO>
     */
    Page<UserDTO> getPageUserActive(Pageable pageable);

    /**
     * getPageUserActive
     *
     * @param pageable pageable
     * @return Page<UserDTO>
     */
    Page<UserDTO> getPageBSUserActive(String storeBranchId, Pageable pageable);

    /**
     * getPageSTUserActive
     *
     * @param storeId  storeId
     * @param pageable pageable
     * @return Page<UserDTO>
     */
    Page<UserDTO> getPageSTUserActive(String storeId, Pageable pageable);

    /**
     * updateStoreBranchId
     *
     * @param id            the id
     * @param storeBranchId the store branch id
     */
    void updateStoreBranchId(String id, String storeBranchId);

    /**
     * updateStoreBranchId
     *
     * @param id      the id
     * @param storeId the store id
     */
    void updateStoreId(String id, String storeId);

    /**
     * updateProfilePicture
     *
     * @param id             the id
     * @param profilePicture the profile picture
     * @param updatedAt      the updated at
     * @param updatedBy      the updated by
     */
    void updateProfilePicture(String id, String profilePicture, LocalDateTime updatedAt, String updatedBy);
}