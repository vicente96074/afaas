package com.kojstarinnovations.ms.inventory.gateway.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EnvironmentService {

    @PostConstruct
    public void logEnvVariables() {

        String frontendUrl = System.getenv("MS_INVENTORY_FRONTEND_URL");
        String configUrl = System.getenv("CONFIG_SERVER_URL");
        String eurekaUrl = System.getenv("EUREKA_SERVER_URL");

        Logger.getLogger("EnvironmentService").info("MS_INVENTORY_FRONTEND_URL: " + frontendUrl);
        Logger.getLogger("EnvironmentService").info("CONFIG_SERVER_URL: " + configUrl);
        Logger.getLogger("EnvironmentService").info("EUREKA_SERVER_URL: " + eurekaUrl);

    }
}