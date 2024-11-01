package com.kojstarinnovations.ms.inventory.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class LoggingFilterConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilterConfig.class);

    @Bean
    public GlobalFilter corsLoggingFilter() {
        return (exchange, chain) -> {
            // Log request headers, including CORS
            logRequest(exchange);

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Log response headers, including CORS
                logResponse(exchange);
            }));
        };
    }

    private void logRequest(ServerWebExchange exchange) {
        logger.info("Incoming Request: Method={}, URL={}, Headers={}, Origin={}, CORS-Headers={}",
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI(),
                exchange.getRequest().getHeaders(),
                exchange.getRequest().getHeaders().getFirst("Origin"),
                exchange.getRequest().getHeaders().getFirst("Access-Control-Request-Headers"));

        if ("websocket".equalsIgnoreCase(exchange.getRequest().getHeaders().getFirst("Upgrade"))) {
            logger.info("WebSocket Upgrade Request Detected");
        }
    }

    private void logResponse(ServerWebExchange exchange) {
        logger.info("Outgoing Response: Status Code={}, Headers={}",
                exchange.getResponse().getStatusCode(),
                exchange.getResponse().getHeaders());
    }
}
