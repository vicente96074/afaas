package com.kojstarinnovations.afaas.us.domain.ucextends;

import com.kojstarinnovations.afaas.commons.data.response.auth.UserResponse;
import com.kojstarinnovations.afaas.commons.ports.input.UseCase;
import com.kojstarinnovations.afaas.us.application.data.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**
 *
 * UserUC
 *
 * @author BalamKiche
 *
 */
public interface UserUC extends UseCase<UserRequest, UserResponse, String> {


    /**
     * Get by username
     *
     * @param username the username to search
     * @return user response search result
     */
    UserResponse getByUsername(String username);

    /**
     * Exists by username
     *
     * @param username the username to search
     * @return boolean exists otherwise false
     */
    boolean existsByUsername(String username);

    /**
     * Exists by email
     *
     * @param email the email to search
     * @return boolean exists otherwise false
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
     * exist by identification and identification type
     *
     * @param identification the person identification
     * @return boolean exists
     */
    boolean existsByIdentification(String identification);

    /**
     * Get page active by store id
     *
     * @param pageable the page to search
     * @return page of user response
     */
    Page<UserResponse> getPageUserActive(Pageable pageable);

    /**
     * Get page user active
     *
     * @param pageable the page to search
     * @return page of user response
     */
    Page<UserResponse> getPageBSUserActive(String storeBranchId, Pageable pageable);

    /**
     * Get page user active
     *
     * @param pageable the page to search
     * @return page of user response
     */
    Page<UserResponse> getPageSTUserActive(String storeId, Pageable pageable);

    /**
     * Update profile picture
     *
     * @param id the id
     * @param profilePicture the profile picture
     */
    void updateProfilePicture(String id, String profilePicture);
}