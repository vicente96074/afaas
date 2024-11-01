package com.kojstarinnovations.afaas.us.infrastructure.adapters.input.controller;

import com.kojstarinnovations.afaas.commons.data.request.PaginationByStoreRequest;
import com.kojstarinnovations.afaas.commons.data.response.auth.UserResponse;
import com.kojstarinnovations.afaas.commons.message.CustomizedMessage;
import com.kojstarinnovations.afaas.us.application.data.request.GrantRolesAndAccessRequest;
import com.kojstarinnovations.afaas.us.application.data.request.UserRequest;
import com.kojstarinnovations.afaas.us.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-service/user")
public class UserController {

    /**
     * register method
     *
     * @param userRequest The new user to be registered
     * @return The response entity
     */
    @PostMapping(value = "/register", produces = "application/json")
    @PreAuthorize("@securityService.hasRole('SUPER_ADMIN') or @securityService.hasRole('CEO') or @securityService.hasRole('REGIONAL_MANAGER')")
    public ResponseEntity<CustomizedMessage> newUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = service.saveWithBasicRolesAndAccesses(userRequest);
        return new ResponseEntity<>(new CustomizedMessage("User registered: " + userResponse.getUsername()), HttpStatus.CREATED);
    }

    /**
     * Details by id method
     *
     * @param id The id of the user
     * @return The response entity
     */
    @GetMapping(value = "/details-by-id", produces = "application/json")
    @PreAuthorize("@securityService.hasRole('SUPER_ADMIN') or @securityService.hasRole('CEO') or @securityService.hasRole('REGIONAL_MANAGER')")
    public ResponseEntity<UserResponse> getById(@RequestParam("id") String id) {
        UserResponse userResponse = service.getById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * Get page user active method
     *
     * @param request The request
     * @return The response entity
     */
    @PostMapping(value = "/get-page-user-active", produces = "application/json")
    @PreAuthorize("@securityService.hasRole('SUPER_ADMIN') or @securityService.hasRole('CEO') or @securityService.hasRole('REGIONAL_MANAGER')")
    public ResponseEntity<Page<UserResponse>> getPageUserActive(@RequestBody PaginationByStoreRequest request) {
        return new ResponseEntity<>(service.getPageUserActive(
                PageRequest.of(
                        request.getPage(),
                        request.getSize(),
                        Sort.by(request.getAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, request.getOrders())
                )), HttpStatus.OK);
    }

    /**
     * Get page user active method
     *
     * @param request The request
     * @return The response entity
     */
    @PostMapping(value = "/get-page-bs-user-active", produces = "application/json")
    @PreAuthorize("@securityService.hasRole('SUPER_ADMIN') or @securityService.hasRole('CEO') or @securityService.hasRole('REGIONAL_MANAGER')")
    public ResponseEntity<Page<UserResponse>> getPageBSUserActive(@RequestBody PaginationByStoreRequest request) {
        return new ResponseEntity<>(service.getPageBSUserActive(
                request.getStoreBranchId(),
                PageRequest.of(
                        request.getPage(),
                        request.getSize(),
                        Sort.by(request.getAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, request.getOrders())
                )), HttpStatus.OK);
    }

    /**
     * Get page user active method
     *
     * @param request The request
     * @return The response entity
     */
    @PostMapping(value = "/get-page-st-user-active", produces = "application/json")
    @PreAuthorize("@securityService.hasRole('SUPER_ADMIN') or @securityService.hasRole('CEO') or @securityService.hasRole('REGIONAL_MANAGER')")
    public ResponseEntity<Page<UserResponse>> getPageSTUserActive(@RequestBody PaginationByStoreRequest request) {
        return new ResponseEntity<>(service.getPageSTUserActive(
                request.getStoreId(),
                PageRequest.of(
                        request.getPage(),
                        request.getSize(),
                        Sort.by(request.getAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, request.getOrders())
                )), HttpStatus.OK);
    }

    /**
     * Grant roles and access method
     *
     * @param request The request
     * @return The response entity
     */
    @PostMapping(value = "/grant-roles-and-access", produces = "application/json")
    @PreAuthorize("@securityService.hasRole('SUPER_ADMIN') or @securityService.hasRole('CEO') or @securityService.hasRole('REGIONAL_MANAGER')")
    public ResponseEntity<CustomizedMessage> grantRolesAndAccess(@Valid @RequestBody GrantRolesAndAccessRequest request) {
        service.grantRolesAndAccessesAndStore(request);
        return new ResponseEntity<>(new CustomizedMessage("Roles, accesos y tienda asignados"), HttpStatus.OK);
    }

    /**
     * Update profile picture method
     *
     * @param id             The id of the user
     * @param profilePicture The profile picture
     * @return The response entity
     */
    @PostMapping(value = "/update-profile-picture", produces = "application/json")
    @PreAuthorize("@securityService.hasRole('SUPER_ADMIN') or @securityService.hasRole('CEO') or @securityService.hasRole('REGIONAL_MANAGER')")
    public ResponseEntity<CustomizedMessage> updateProfilePicture(@RequestParam("id") String id, @RequestParam("profilePicture") String profilePicture) {
        service.updateProfilePicture(id, profilePicture);
        return new ResponseEntity<>(new CustomizedMessage("Profile picture updated"), HttpStatus.OK);
    }

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    private final UserService service;
}