package com.kojstarinnovations.afaas.us.util;

import com.kojstarinnovations.afaas.commons.emuns.RolName;
import com.kojstarinnovations.afaas.us.application.data.request.RolRequest;
import com.kojstarinnovations.afaas.us.domain.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * CreateRoles class
 *
 * @author Augusto Vicente
 */
@Component
public class CreateRoles {

    /**
     * Method to create the roles
     */

    public void run() {

        try {
            rolService.save(new RolRequest(RolName.SUPER_ADMIN));
            rolService.save(new RolRequest(RolName.CEO));
            rolService.save(new RolRequest(RolName.REGIONAL_MANAGER));
            rolService.save(new RolRequest(RolName.ADMINISTRATOR));
            rolService.save(new RolRequest(RolName.SELLER));
            rolService.save(new RolRequest(RolName.CASHIER));
            rolService.save(new RolRequest(RolName.INVENTORY_MANAGER));
            rolService.save(new RolRequest(RolName.LOGISTIC_STAFF));
            rolService.save(new RolRequest(RolName.MARKETING_STAFF));
            rolService.save(new RolRequest(RolName.MAINTENANCE_STAFF));
            rolService.save(new RolRequest(RolName.CUSTOMER));
            rolService.save(new RolRequest(RolName.SUPPLIER));
            rolService.save(new RolRequest(RolName.DELIVERY_PERSON));
            rolService.save(new RolRequest(RolName.SECURITY_GUARD));
            rolService.save(new RolRequest(RolName.CLEANER));
        } catch (Exception e) {
            Logger.getLogger("CreateRoles").info("Roles already created");
        }

        Logger.getLogger("CreateRoles").info("Roles created");
    }

    @Autowired
    public CreateRoles(RolService rolService) {
        this.rolService = rolService;
    }

    private final RolService rolService;
}