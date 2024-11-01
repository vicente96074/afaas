package com.kojstarinnovations.afaas.us.infrastructure.adapters.config;

import com.kojstarinnovations.afaas.us.jwt.JwtEntryPoint;
import com.kojstarinnovations.afaas.us.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class configures the security filter chain.
 *
 * @author Augusto Vicente
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(exc -> exc.authenticationEntryPoint(jwtEntryPoint));

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private static final String[] AUTH_WHITELIST = {
            "/ws/**",
            "/email-password/**",
            "/configuration/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/favicon.ico"
    };

    @Autowired
    public MainSecurity(JwtTokenFilter jwtTokenFilter, JwtEntryPoint jwtEntryPoint) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.jwtEntryPoint = jwtEntryPoint;
    }

    private final JwtTokenFilter jwtTokenFilter;
    private final JwtEntryPoint jwtEntryPoint;
}
