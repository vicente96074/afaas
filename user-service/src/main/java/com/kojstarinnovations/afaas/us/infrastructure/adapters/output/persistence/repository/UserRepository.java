package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository;

import com.kojstarinnovations.afaas.commons.emuns.Status;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * UserRepository is used to access the database and perform CRUD operations on the user table
 *
 * @Author: Augusto Vicente and Kojstar Innovations
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * This method is used to find a user by its username
     *
     * @param username The username of the user
     * @return An optional of the user
     */
    Optional<User> findByUsername(String username);

    /**
     * This method is used to find a user by its email
     *
     * @param username The email of the user
     * @return An optional of the user
     */
    boolean existsByUsername(String username);

    /**
     * This method is used to find a user by its email
     *
     * @param email The email of the user
     * @return An optional of the user
     */
    boolean existsByEmail(String email);

    /**
     * This method is used to find a user by its phone
     *
     * @param phone The phone of the user
     * @return An optional of the user
     */
    boolean existsByPhone(String phone);

    /**
     * This method is used to find a user by its username or email
     *
     * @param username the username of the user
     * @param email    the email of the user
     * @return An optional of the user
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * This method is used to find a user by its person identification and person identification type
     *
     * @param identification The person identification of the user
     * @return boolean exists
     */
    boolean existsByIdentification(String identification);

    /**
     * This method is used to find a user by its store id
     *
     * @param pageable The pageable object
     * @return A page of users
     */
    @Query("SELECT u FROM users u WHERE u.status = :status")
    Page<User> getFindByStatus(@Param("status") Status status, Pageable pageable);

    /**
     * This method is used to find a user by its status
     *
     * @param status   The status of the user
     * @param pageable The pageable object
     * @return A page of users
     */
    @Query("SELECT u FROM users u WHERE u.status = :status AND u.storeBranchId = :storeBranchId")
    Page<User> findActiveByBSAndStatus(@Param("status") Status status, @Param("storeBranchId") String branchStoreId, Pageable pageable);

    /**
     * This method is used to find a user by its status
     *
     * @param status   The status of the user
     * @param pageable The pageable object
     * @return A page of users
     */
    @Query("SELECT u FROM users u WHERE u.status = :status AND u.storeId = :storeId")
    Page<User> findActiveBySTAndStatus(@Param("status") Status status, @Param("storeId") String storeId, Pageable pageable);

    /**
     * Grant store id to user
     *
     * @param id            the id of the user
     * @param storeBranchId the store id
     */
    @Modifying
    @Query("UPDATE users u SET u.storeBranchId = :storeBranchId WHERE u.id = :id")
    void updateStoreBranchId(@Param("id") String id, @Param("storeBranchId") String storeBranchId);

    /**
     * Update store id
     *
     * @param id      the id of the user
     * @param storeId the store id
     */
    @Modifying
    @Query("UPDATE users u SET u.storeId = :storeId WHERE u.id = :id")
    void updateStoreId(@Param("id") String id, @Param("storeId") String storeId);

    /**
     * Update user profile picture
     *
     * @param id                the id of the user
     * @param urlProfilePicture the url of the profile picture
     * @param updatedAt         the updated at
     * @param updatedBy         the updated by
     */
    @Modifying
    @Query("UPDATE users u SET u.urlProfilePicture = :urlProfilePicture, u.updatedAt = :updatedAt, u.updatedBy = :updatedBy WHERE u.id = :id")
    void updateProfilePicture(@Param("id") String id, @Param("urlProfilePicture") String urlProfilePicture, @Param("updatedAt") LocalDateTime updatedAt, @Param("updatedBy") String updatedBy);
}