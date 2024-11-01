package com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.repository;

import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository is used to access the database and perform CRUD operations on the user table
 *
 * @Author: Augusto Vicente and Kojstar Innovations
 */
@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    /**
     * This method is used to find a user by its username
     *
     * @param username The username of the user
     * @return An optional of the user
     */
    Optional<User> findByUsername(String username);

    /**
     *  This method is used to find a user by its username or email
     *
     * @param username the username of the user
     * @param email the email of the user
     * @return An optional of the user
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * This method is used to check if a user exists by its username
     *
     * @param username The username of the user
     * @return A boolean value
     */
    boolean existsByUsername(String username);
}