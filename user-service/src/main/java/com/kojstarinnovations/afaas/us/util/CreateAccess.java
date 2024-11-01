package com.kojstarinnovations.afaas.us.util;

import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import com.kojstarinnovations.afaas.us.application.data.request.AccessRequest;
import com.kojstarinnovations.afaas.us.domain.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CreateAccess {

    /**
     * Method to create the access
     */
    public void run() {
        try {
            accessService.save(new AccessRequest(AccessName.ADMINISTRATION));
            accessService.save(new AccessRequest(AccessName.QUOTE));
            accessService.save(new AccessRequest(AccessName.PURCHASES));
            accessService.save(new AccessRequest(AccessName.SALES));
            accessService.save(new AccessRequest(AccessName.CASH));
            accessService.save(new AccessRequest(AccessName.INVENTORY));
            accessService.save(new AccessRequest(AccessName.SUPPLIERS));
            accessService.save(new AccessRequest(AccessName.CLIENTS));
            accessService.save(new AccessRequest(AccessName.USERS));
            accessService.save(new AccessRequest(AccessName.REPORTS));
            accessService.save(new AccessRequest(AccessName.SETTINGS));

            accessService.save(new AccessRequest(AccessName.WAREHOUSE));
            accessService.save(new AccessRequest(AccessName.LOGISTICS));
            accessService.save(new AccessRequest(AccessName.ADMINISTRATIVE_OFFICE));
            accessService.save(new AccessRequest(AccessName.CUSTOMER_SERVICE));
            accessService.save(new AccessRequest(AccessName.MARKETING));
            accessService.save(new AccessRequest(AccessName.HUMAN_RESOURCES));
            accessService.save(new AccessRequest(AccessName.ACCOUNTING));
            accessService.save(new AccessRequest(AccessName.FINANCE));
            accessService.save(new AccessRequest(AccessName.MANAGEMENT));
            accessService.save(new AccessRequest(AccessName.SECURITY));
            accessService.save(new AccessRequest(AccessName.IT));
            accessService.save(new AccessRequest(AccessName.PROCUREMENT));
            accessService.save(new AccessRequest(AccessName.LEGAL));
            accessService.save(new AccessRequest(AccessName.PRODUCTION));
            accessService.save(new AccessRequest(AccessName.OPERATIONS));
            accessService.save(new AccessRequest(AccessName.MAINTENANCE));
            accessService.save(new AccessRequest(AccessName.DISTRIBUTION));
        } catch (Exception e) {
            Logger.getLogger("CreateAccess").info("Access already created");
        }

        Logger.getLogger("CreateAccess").info("Access created");
    }

    @Autowired
    public CreateAccess(AccessService accessService) {
        this.accessService = accessService;
    }

    private final AccessService accessService;
}
