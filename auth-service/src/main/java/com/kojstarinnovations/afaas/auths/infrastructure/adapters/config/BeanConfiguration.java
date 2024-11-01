package com.kojstarinnovations.afaas.auths.infrastructure.adapters.config;

import com.kojstarinnovations.afaas.auths.domain.eventlistener.EventListenerAdapter;
import com.kojstarinnovations.afaas.commons.mapper.ModelMapperCustomized;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Bean configuration
 *
 * @author Augusto Vicente
 */
@Configuration
public class BeanConfiguration {

    /**
     * EventPublisher
     *
     * @return EventPublisher
     */
    @Bean
    public EventListenerAdapter eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new EventListenerAdapter(applicationEventPublisher);
    }

    /**
     * ModelMapperCustomized
     *
     * @return ModelMapperCustomized
     */
    @Bean
    public ModelMapperCustomized modelMapperCustomized() {
        return new ModelMapperCustomized();
    }

    /**
     * PasswordEncoder
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager
     *
     * @param authenticationConfiguration authenticationConfiguration
     * @return AuthenticationManager
     * @throws Exception Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
