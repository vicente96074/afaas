package com.kojstarinnovations.afaas.us;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Primary entry point for the user service.
 *
 * @Author: Augusto Vicente
 */
@SpringBootApplication(scanBasePackages = "com.kojstarinnovations.ms.inventory.us")
@EnableDiscoveryClient
@EnableFeignClients
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}