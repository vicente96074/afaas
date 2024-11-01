package com.kojstarinnovations.afaas.auths.domain.service;

import com.kojstarinnovations.afaas.auths.domain.opextends.AuthOP;
import com.kojstarinnovations.afaas.auths.domain.ucextends.AuthUC;
import com.kojstarinnovations.afaas.auths.jwt.JwtProvider;
import com.kojstarinnovations.afaas.commons.data.dto.JwtDTO;
import com.kojstarinnovations.afaas.commons.ports.output.EventPublisher;
import com.kojstarinnovations.afaas.commons.ports.output.TransactionId;
import com.kojstarinnovations.afaas.commons.ports.output.event.DisplayEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * UserService
 *
 * @author BalamKiche
 */
@Service
@Transactional
public class AuthService implements AuthUC {

    public JwtDTO validate(String token) {
        if (!jwtProvider.validateToken(token)) {
            return null;
        }

        String username = jwtProvider.getUsernameFromToken(token);
        if (!outputPort.existsByUsername(username)) {
            return null;

        }

        eventPublisher.handle(new DisplayEvent<>("Token validated", LocalDateTime.now(), "SYSTEM", "UserService", "validate", TransactionId.generateTransactionId(), username));

        return new JwtDTO(token);
    }

    @Autowired
    public AuthService(
            EventPublisher eventPublisher,
            AuthOP outputPort,
            JwtProvider jwtProvider
    ) {
        this.eventPublisher = eventPublisher;
        this.outputPort = outputPort;
        this.jwtProvider = jwtProvider;
    }

    private final EventPublisher eventPublisher;
    private final AuthOP outputPort;
    private final JwtProvider jwtProvider;
}