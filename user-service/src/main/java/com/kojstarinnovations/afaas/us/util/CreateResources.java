package com.kojstarinnovations.afaas.us.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateResources implements CommandLineRunner {

    @Override
    public void run(String... args) {
        createAccess.run();
        createRoles.run();
        createUser.run();
    }

    @Autowired
    public CreateResources(
            CreateAccess createAccess,
            CreateRoles createRoles,
            CreateUser createUser
    ) {
        this.createAccess = createAccess;
        this.createRoles = createRoles;
        this.createUser = createUser;
    }

    private final CreateAccess createAccess;
    private final CreateRoles createRoles;
    private final CreateUser createUser;
}
