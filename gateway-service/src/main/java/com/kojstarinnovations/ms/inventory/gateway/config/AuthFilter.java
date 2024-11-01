package com.kojstarinnovations.ms.inventory.gateway.config;

import com.kojstarinnovations.ms.inventory.gateway.dto.JwtDTO;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.logging.Logger;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.BAD_REQUEST, "Authorization header is missing");
            }

            String tokenHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            String[] chunks = tokenHeader.split(" ");
            if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
                return onError(exchange, HttpStatus.BAD_REQUEST, "Token format is invalid");
            }

            return webClient.build()
                    .post()
                    .uri("http://auth-service/auth/validate?token=" + chunks[1])
                    .retrieve()
                    .bodyToMono(JwtDTO.class)
                    .flatMap(jwtDTO -> {
                        // Token válido, continuar con la cadena de filtros
                        exchange.getRequest().mutate().header("X-JWT-Token", jwtDTO.getToken()).build();
                        return chain.filter(exchange);
                    })
                    .onErrorResume(WebClientResponseException.class, e -> {
                        // Manejo de errores del servicio de autenticación
                        if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                            return onError(exchange, HttpStatus.SERVICE_UNAVAILABLE, "Auth service is unavailable");
                        } else {
                            return onError(exchange, HttpStatus.UNAUTHORIZED, "Token is invalid or expired");
                        }
                    })
                    .onErrorResume(e -> {
                        // Otros errores, como que el servicio no esté disponible
                        return onError(exchange, HttpStatus.SERVICE_UNAVAILABLE, "Service unavailable");
                    });
        };
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status, String message) {
        Logger.getLogger(AuthFilter.class.getName()).severe("Error: " + message);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        DataBufferFactory bufferFactory = response.bufferFactory();
        DataBuffer buffer = bufferFactory.wrap(("{\"error\": \"" + message + "\"}").getBytes());

        return response.writeWith(Mono.just(buffer));
    }

    public static class Config {
    }
}