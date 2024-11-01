package com.kojstarinnovations.ms.inventory.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication(scanBasePackages = "com.kojstarinnovations.ms.inventory.cs")
@EnableConfigServer
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}