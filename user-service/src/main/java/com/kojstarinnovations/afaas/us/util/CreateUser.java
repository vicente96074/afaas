package com.kojstarinnovations.afaas.us.util;

import com.kojstarinnovations.afaas.us.application.data.request.UserRequest;
import com.kojstarinnovations.afaas.us.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class CreateUser {

    public void run() {
        try {

            //Role
            Set<String> rolesUserOne = new HashSet<>();
            rolesUserOne.add("super_admin");

            //Access
            UserRequest requestOne = getUserRequest(rolesUserOne);

            userService.saveWithRolesAndAccesses(requestOne);
        } catch (Exception e) {
            Logger.getLogger("CreateUser").info(e.getMessage());
        }
    }

    private static UserRequest getUserRequest(Set<String> rolesUserOne) {
        Set<String> accessesUserOne = new HashSet<>();
        accessesUserOne.add("administration");

        return new UserRequest(
                "Augusto",
                "Vicente y Vicente",
                "3116428530805",
                "vicente96074",
                "vicente96074@gmail.com",
                "2023",
                LocalDate.of(1998, 5, 7),
                null,
                "SBkaasds",
                "STasdasd",
                "502 44834598",
                "Zona 1, Quetzaltenango",
                rolesUserOne,
                accessesUserOne
        );
    }

    @Autowired
    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
}