package com.kojstarinnovations.afaas.us.application.uc;

import com.kojstarinnovations.afaas.commons.data.response.basic.BooleanJSON;
import jakarta.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Branch service
 *
 * @Author: Augusto Vicente
 */
@FeignClient(name = "branch-service")
public interface BranchServiceFeignC {

    @GetMapping(value = "/api/branch-service/store/exists-by-id-public", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<BooleanJSON> existsStoreById(@RequestParam("id") String id);

    @GetMapping(value = "/api/branch-service/store-branch/exists-by-id-public", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<BooleanJSON> existsStoreBranchById(@RequestParam("id") String id);
}