package com.kojstarinnovations.afaas.auths.domain.service;

import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.entity.User;
import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.pmimpl.AuthPM;
import com.kojstarinnovations.afaas.auths.infrastructure.adapters.persistence.repository.AuthRepository;
import com.kojstarinnovations.afaas.commons.data.dto.PrincipalUser;
import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * UserDetailsServiceImpl
 *
 * @author Augusto Vicente
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * loadUserByUsername
     *
     * @param usernameOrEmail the username or email
     * @return UserDetails
     * @throws UsernameNotFoundException the username not found exception
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> opUser = repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (opUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        UserDTO userDto = persistenceMapper.entityToDtoWithAccessAndRoles(opUser.get());
        return PrincipalUser.build(userDto);
    }

    @Autowired
    public UserDetailsServiceImpl(AuthRepository repository, AuthPM persistenceMapper) {
        this.repository = repository;
        this.persistenceMapper = persistenceMapper;
    }

    private final AuthRepository repository;
    private final AuthPM persistenceMapper;
}