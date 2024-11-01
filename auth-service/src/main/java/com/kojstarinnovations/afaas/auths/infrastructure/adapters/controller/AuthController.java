package com.kojstarinnovations.afaas.auths.infrastructure.adapters.controller;

import com.kojstarinnovations.afaas.auths.domain.service.AuthService;
import com.kojstarinnovations.afaas.auths.jwt.JwtProvider;
import com.kojstarinnovations.afaas.commons.data.dto.JwtDTO;
import com.kojstarinnovations.afaas.commons.data.dto.PrincipalUser;
import com.kojstarinnovations.afaas.commons.data.request.auth.LoginUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Auth controller
 *
 * @author Augusto Vicente
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUser loginUser) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
        );

        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> roles = principalUser.getAuthorities();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        JwtDTO jwtDto = new JwtDTO(jwt);

        Logger.getLogger("AuthController").info("User logged with username: " + loginUser.getUsername() +
                                                " at " + LocalDateTime.now() +
                                                " with token: " + jwtDto.getToken());

        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<JwtDTO> validate(@RequestParam String token) {
        JwtDTO tokenDto = authService.validate(token);
        if (tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tokenDto);
    }

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            AuthService authService,
            JwtProvider jwtProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtProvider jwtProvider;
}