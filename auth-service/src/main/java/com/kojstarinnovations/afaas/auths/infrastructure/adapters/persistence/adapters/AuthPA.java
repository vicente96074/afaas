package com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.adapters;

import com.kojstarinnovations.afaas.auths.domain.opextends.AuthOP;
import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.repository.AuthRepository;
import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.pmimpl.AuthPM;
import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * UserPA
 *
 * @author Augusto Vicente
 */
@Component
public class AuthPA implements AuthOP {

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

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Autowired
    public AuthPA(AuthPM persistenceMapper, AuthRepository repository) {
        this.persistenceMapper = persistenceMapper;
        this.repository = repository;
    }
    private final AuthPM persistenceMapper;
    private final AuthRepository repository;
}