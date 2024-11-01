package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.dto.UserDTO;
import com.kojstarinnovations.afaas.commons.data.helper.IDsHelper;
import com.kojstarinnovations.afaas.commons.data.request.JsonUserIdAndURLImg;
import com.kojstarinnovations.afaas.commons.data.response.auth.AccessResponse;
import com.kojstarinnovations.afaas.commons.data.response.auth.RolResponse;
import com.kojstarinnovations.afaas.commons.data.response.auth.UserResponse;
import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import com.kojstarinnovations.afaas.commons.emuns.RolName;
import com.kojstarinnovations.afaas.commons.exception.DuplicateException;
import com.kojstarinnovations.afaas.commons.exception.NotFoundException;
import com.kojstarinnovations.afaas.commons.message.CustomizedMessage;
import com.kojstarinnovations.afaas.commons.ports.output.EventPublisher;
import com.kojstarinnovations.afaas.commons.ports.output.TransactionId;
import com.kojstarinnovations.afaas.commons.ports.output.event.*;
import com.kojstarinnovations.afaas.us.application.data.request.GrantRolesAndAccessRequest;
import com.kojstarinnovations.afaas.us.application.data.request.UserAccessRequest;
import com.kojstarinnovations.afaas.us.application.data.request.UserRequest;
import com.kojstarinnovations.afaas.us.application.data.request.UserRolRequest;
import com.kojstarinnovations.afaas.us.domain.dmimpl.UserDM;
import com.kojstarinnovations.afaas.us.domain.opextends.UserOP;
import com.kojstarinnovations.afaas.us.domain.ucextends.UserUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

/**
 * UserService
 *
 * @author BalamKiche
 */
@Service
@Transactional
public class UserService implements UserUC {

    /**
     * existsById method
     *
     * @param id the id of the entity.
     * @return boolean true if exists otherwise false.
     */
    @Override
    public boolean existsById(String id) {
        boolean exists = outputPort.existsById(id);

        eventPublisher.handle(new ExistenceCheckedEvent<>("User existence checked by ID", LocalDateTime.now(), "SYSTEM", "UserService", "existsById", TransactionId.generateTransactionId(), id));

        return exists;
    }

    /**
     * save method
     *
     * @param request the request of the entity.
     * @return response the response of the entity.
     */
    @Override
    public UserResponse save(UserRequest request) {

        request.setIdentification(IDsHelper.formatDpi(request.getIdentification()));

        if (existsByIdentification(request.getIdentification())) {
            throw new DuplicateException("Este DPI ya ha sido registrado");
        }

        if (existsByUsername(request.getUsername())) {
            throw new DuplicateException("El nombre de usuario ya existe");
        }

        if (existsByEmail(request.getEmail())) {
            throw new DuplicateException("El correo electrónico ya existe");
        }

        if (existsByPhone(request.getPhone())) {
            throw new DuplicateException("El número de teléfono ya existe");
        }


        UserDTO dto = domainMapper.requestToDto(request);
        dto.setId(null);
        dto = (UserDTO) auditAttributeServiceSecurity.getAuditAttributesForSystem(dto); // Add audit attributes for system
        dto.setStoreId(request.getStoreId());
        dto.setStoreBranchId(request.getStoreBranchId());
        dto = outputPort.save(dto);

        eventPublisher.handle(new EnrollingEvent<>("User enrolled", LocalDateTime.now(), "SYSTEM", "UserService", "save", TransactionId.generateTransactionId(), dto));

        return domainMapper.dtoToResponse(dto);
    }

    /**
     * deleteById method
     *
     * @param id the id of the entity.
     */
    @Override
    public void deleteById(String id) {
        outputPort.deleteById(id);

        eventPublisher.handle(new DeletionEvent<>("User deleted by ID", LocalDateTime.now(), "SYSTEM", "UserService", "deleteById", TransactionId.generateTransactionId(), id));
    }

    /**
     * getById method
     *
     * @param ID the id of the entity.
     * @return modelDto the modelDto of the entity.
     */
    @Override
    public UserResponse getById(String ID) {
        Optional<UserDTO> userDto = outputPort.getById(ID);

        eventPublisher.handle(new DisplayEvent<>("User displayed by ID", LocalDateTime.now(), "SYSTEM", "UserService", "getById", TransactionId.generateTransactionId(), ID));

        return domainMapper.dtoToResponse(userDto.orElseThrow(() -> new NotFoundException("User not found by ID")));
    }

    /**
     * Get all objects
     *
     * @param pageable the pageable object
     * @return Page<Response>
     */
    @Override
    public Page<UserResponse> getPage(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get all objects
     *
     * @return QueryResponse
     */
    @Override
    public List<UserResponse> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * getByUsername method
     *
     * @param username the username of the entity.
     * @return modelDto the modelDto of the entity.
     */
    @Override
    public UserResponse getByUsername(String username) {
        Optional<UserDTO> userDto = outputPort.getByUsername(username);

        eventPublisher.handle(new DisplayEvent<>("User displayed by username", LocalDateTime.now(), "SYSTEM", "UserService", "getByUsername", TransactionId.generateTransactionId(), username));

        return domainMapper.dtoToResponse(userDto.orElseThrow(() -> new NotFoundException("User not found by username")));
    }

    /**
     * exists by username method
     *
     * @param username the username of the entity.
     * @return boolean true if exists otherwise false.
     */
    @Override
    public boolean existsByUsername(String username) {
        boolean exists = outputPort.existsByUsername(username);

        eventPublisher.handle(new ExistenceCheckedEvent<>("User existence checked by username", LocalDateTime.now(), "SYSTEM", "UserService", "existsByUsername", TransactionId.generateTransactionId(), username));

        return exists;
    }

    /**
     * exists by email method
     *
     * @param email the email of the entity.
     * @return boolean true if exists otherwise false.
     */
    @Override
    public boolean existsByEmail(String email) {
        boolean exists = outputPort.existsByEmail(email);

        eventPublisher.handle(new ExistenceCheckedEvent<>("User existence checked by email", LocalDateTime.now(), "SYSTEM", "UserService", "existsByEmail", TransactionId.generateTransactionId(), email));

        return exists;
    }

    /**
     * existsByPhone
     *
     * @param phone phone
     * @return boolean
     */
    @Override
    public boolean existsByPhone(String phone) {
        boolean exists = outputPort.existsByPhone(phone);

        eventPublisher.handle(new ExistenceCheckedEvent<>("User existence checked by phone", LocalDateTime.now(), "SYSTEM", "UserService", "existsByPhone", TransactionId.generateTransactionId(), phone));

        return exists;
    }

    /**
     * existsByPersonIdentificationAndPersonIdentificationType method
     *
     * @param identification the person identification
     * @return exists
     */
    @Override
    public boolean existsByIdentification(String identification) {
        boolean exists = outputPort.existsByIdentification(identification);

        eventPublisher.handle(new ExistenceCheckedEvent<>("User existence checked by person identification and person identification type", LocalDateTime.now(), "SYSTEM", "UserService", "existsByPersonIdentificationAndPersonIdentificationType", TransactionId.generateTransactionId(), identification));

        return exists;
    }

    @Transactional
    public UserResponse saveWithBasicRolesAndAccesses(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserResponse userResponse = save(userRequest);

        Set<String> roles = new HashSet<>();
        roles.add("cashier");

        Set<String> accesses = new HashSet<>();
        accesses.add("cash");

        Set<RolResponse> rolesResponses = getRoleFromDB(roles);
        Set<AccessResponse> accessesResponses = getAccessFromDB(accesses);

        userResponse.setRolResponses(rolesResponses);
        userResponse.setAccessResponses(accessesResponses);

        for (RolResponse rol : rolesResponses) {
            assignRole(userResponse.getId(), rol.getId());
        }

        for (AccessResponse access : accessesResponses) {
            assignAccess(userResponse.getId(), access.getId());
        }

        notifyFrontend("users", userResponse);
        return userResponse;
    }

    @Transactional
    public void saveWithRolesAndAccesses(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserResponse userResponse = save(userRequest);

        Set<RolResponse> rolesResponses = getRoleFromDB(userRequest.getRolesRequest());
        Set<AccessResponse> accessesResponses = getAccessFromDB(userRequest.getAccessesRequest());

        userResponse.setRolResponses(rolesResponses);
        userResponse.setAccessResponses(accessesResponses);

        for (RolResponse rol : rolesResponses) {
            assignRole(userResponse.getId(), rol.getId());
        }

        for (AccessResponse access : accessesResponses) {
            assignAccess(userResponse.getId(), access.getId());
        }
    }

    public void grantRolesAndAccessesAndStore(GrantRolesAndAccessRequest request) {

        Logger.getLogger("UserService").info("Request: " + request);

        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            throw new NotFoundException("No se han encontrado roles a asignar");
        }

        if (request.getAccesses() == null || request.getAccesses().isEmpty()) {
            throw new NotFoundException("No se han encontrado accesos a asignar");
        }

        if (!existsById(request.getUserId())) {
            throw new NotFoundException("Usuario no encontrado por ID");
        }

        if (!this.branchService.existsStoreById(request.getStoreId())) {
            throw new NotFoundException("No se ha encontrado la tienda");
        }

        if (!this.branchService.existsStoreBranchById(request.getStoreBranchId())) {
            throw new NotFoundException("No se ha encontrado la sucursal de la tienda");
        }

        //List to Set
        Set<String> roles = new HashSet<>(request.getRoles());
        Set<String> accesses = new HashSet<>(request.getAccesses());

        Set<RolResponse> rolesResponses = getRoleFromDB(roles);
        Set<AccessResponse> accessesResponses = getAccessFromDB(accesses);

        //Delete hold roles and accesses by user
        userRolService.deleteByUserId(request.getUserId());
        userAccessService.deleteByUserId(request.getUserId());

        // Using lambda expression
        rolesResponses.forEach(
                rol -> assignRole(request.getUserId(), rol.getId())
        );

        accessesResponses.forEach(
                access -> assignAccess(request.getUserId(), access.getId())
        );

        //Assign store
        outputPort.updateStoreBranchId(request.getUserId(), request.getStoreBranchId());
        outputPort.updateStoreId(request.getUserId(), request.getStoreId());

        //Send message to websocket
        notifyFrontend("users", new CustomizedMessage("Roles, accesos y tienda asignados"));
    }

    /**
     * assignRole method
     *
     * @param userId the user id
     * @param roleId the role id
     */
    public void assignRole(String userId, int roleId) {

        boolean existsUserById = existsById(userId);
        boolean existsRolById = rolService.existsById(roleId);

        if (!existsUserById) {
            throw new NotFoundException("User not found by ID");
        }

        if (!existsRolById) {
            throw new NotFoundException("Rol not found by ID");
        }

        UserRolRequest userRolRequest = new UserRolRequest();

        userRolRequest.setUserId(userId);
        userRolRequest.setRolId(roleId);
        userRolService.save(userRolRequest);
    }

    public void assignAccess(String userId, int accessId) {

        boolean existsUserById = existsById(userId);
        boolean existsAccessById = accessService.existsById(accessId);

        if (!existsUserById) {
            throw new NotFoundException("User not found by ID");
        }

        if (!existsAccessById) {
            throw new NotFoundException("Access not found by ID");
        }

        UserAccessRequest userAccessRequest = new UserAccessRequest();

        userAccessRequest.setUserId(userId);
        userAccessRequest.setAccessId(accessId);

        userAccessService.save(userAccessRequest);
    }

    /**
     * saveRolesFromRequest method
     *
     * @param roles the roles
     * @return rolesResponses the roles responses
     */
    public Set<RolResponse> getRoleFromDB(Set<String> roles) {
        Set<RolResponse> rolesResponses = new HashSet<>();

        if (roles.contains("super_admin")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.SUPER_ADMIN);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("ceo")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.CEO);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("regional_manager")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.REGIONAL_MANAGER);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("administrator")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.ADMINISTRATOR);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("seller")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.SELLER);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("cashier")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.CASHIER);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("inventory_manager")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.INVENTORY_MANAGER);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("logistic_staff")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.LOGISTIC_STAFF);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("marketing_staff")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.MARKETING_STAFF);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("maintenance_staff")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.MAINTENANCE_STAFF);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("customer")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.CUSTOMER);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("supplier")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.SUPPLIER);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("delivery_person")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.DELIVERY_PERSON);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("security_guard")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.SECURITY_GUARD);
            rolesResponses.add(rolResponse);
        } else if (roles.contains("cleaner")) {
            RolResponse rolResponse = rolService.getByRolName(RolName.CLEANER);
            rolesResponses.add(rolResponse);
        }

        return rolesResponses;
    }

    public Set<AccessResponse> getAccessFromDB(Set<String> accesses) {
        Set<AccessResponse> accessResponses = new HashSet<>();

        if (accesses.contains("administration")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.ADMINISTRATION);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("quote")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.QUOTE);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("purchases")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.PURCHASES);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("sales")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.SALES);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("cash")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.CASH);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("inventory")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.INVENTORY);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("suppliers")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.SUPPLIERS);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("clients")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.CLIENTS);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("users")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.USERS);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("reports")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.REPORTS);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("settings")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.SETTINGS);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("warehouse")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.WAREHOUSE);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("logistics")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.LOGISTICS);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("administrative_office")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.ADMINISTRATIVE_OFFICE);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("customer_service")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.CUSTOMER_SERVICE);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("marketing")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.MARKETING);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("human_resources")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.HUMAN_RESOURCES);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("accounting")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.ACCOUNTING);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("finance")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.FINANCE);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("management")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.MANAGEMENT);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("security")) {
            AccessResponse accessResponse = accessService.getByAccessName(AccessName.SECURITY);
            accessResponses.add(accessResponse);
        }
        if (accesses.contains("it")) {
            AccessResponse access = accessService.getByAccessName(AccessName.IT);
            accessResponses.add(access);
        }
        if (accesses.contains("procurement")) {
            AccessResponse access = accessService.getByAccessName(AccessName.PROCUREMENT);
            accessResponses.add(access);
        }
        if (accesses.contains("legal")) {
            AccessResponse access = accessService.getByAccessName(AccessName.LEGAL);
            accessResponses.add(access);
        }
        if (accesses.contains("production")) {
            AccessResponse access = accessService.getByAccessName(AccessName.PRODUCTION);
            accessResponses.add(access);
        }
        if (accesses.contains("operations")) {
            AccessResponse access = accessService.getByAccessName(AccessName.OPERATIONS);
            accessResponses.add(access);
        }
        if (accesses.contains("maintenance")) {
            AccessResponse access = accessService.getByAccessName(AccessName.MAINTENANCE);
            accessResponses.add(access);
        }
        if (accesses.contains("distribution")) {
            AccessResponse access = accessService.getByAccessName(AccessName.DISTRIBUTION);
            accessResponses.add(access);
        }

        return accessResponses;
    }

    /**
     * Get page active by store id
     *
     * @param pageable the pageable object
     * @return Page<UserResponse>
     */
    @Override
    public Page<UserResponse> getPageUserActive(Pageable pageable) {
        Page<UserResponse> page = outputPort.getPageUserActive(pageable)
                .map(domainMapper::dtoToResponse);

        eventPublisher.handle(new DisplayEvent<>("User displayed by store id", LocalDateTime.now(), "SYSTEM", "UserService", "getPageUserActive", TransactionId.generateTransactionId(), "all"));

        return page;
    }

    @Override
    public Page<UserResponse> getPageBSUserActive(String storeBranchId, Pageable pageable) {
        Page<UserResponse> page = outputPort.getPageBSUserActive(storeBranchId, pageable)
                .map(domainMapper::dtoToResponse);

        eventPublisher.handle(new DisplayEvent<>("User displayed by store id", LocalDateTime.now(), "SYSTEM", "UserService", "getPageActiveByStore", TransactionId.generateTransactionId(), pageable));

        return page;
    }

    @Override
    public Page<UserResponse> getPageSTUserActive(String storeId, Pageable pageable) {
        Page<UserResponse> page = outputPort.getPageSTUserActive(storeId, pageable)
                .map(domainMapper::dtoToResponse);

        eventPublisher.handle(new DisplayEvent<>("User displayed by store id", LocalDateTime.now(), "SYSTEM", "UserService", "getPageActiveByStore", TransactionId.generateTransactionId(), pageable));

        return page;
    }

    /**
     * Update profile picture
     *
     * @param id             the id
     * @param profilePicture the profile picture
     */
    @Override
    public void updateProfilePicture(String id, String profilePicture) {
        outputPort.updateProfilePicture(id, profilePicture, LocalDateTime.now(), userDetailsService.principalUserFromAuthentication().getId());

        eventPublisher.handle(new ModificationEvent<>("User profile picture updated", LocalDateTime.now(), "SYSTEM", "UserService", "updateProfilePicture", TransactionId.generateTransactionId(), id));

        notifyFrontend("users/profile-picture", JsonUserIdAndURLImg.of(id, profilePicture));
    }

    /**
     * Notify to the frontend that the data has changed
     */
    public <T> void notifyFrontend(String topic, T content) {
        notificationService.notifyFrontend(topic, content);
    }

    /**
     * Update object by id
     *
     * @param createRequest the object to be updated
     * @param s             id of the object to be updated
     * @return QueryResponse the updated object
     */
    @Override
    public UserResponse updateById(UserRequest createRequest, String s) {
        return null;
    }

    @Autowired
    public UserService(
            AuditAttributeServiceSecurity auditAttributeServiceSecurity,
            PasswordEncoder passwordEncoder,
            RolService rolService,
            AccessService accessService,
            UserRolService userRolService,
            UserAccessService userAccessService,
            EventPublisher eventPublisher,
            UserOP outputPort,
            UserDM domainMapper,
            NotificationService notificationService,
            UserDetailsServiceImpl userDetailsService,
            BranchService branchService
    ) {
        this.auditAttributeServiceSecurity = auditAttributeServiceSecurity;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
        this.accessService = accessService;
        this.userRolService = userRolService;
        this.userAccessService = userAccessService;
        this.eventPublisher = eventPublisher;
        this.outputPort = outputPort;
        this.domainMapper = domainMapper;
        this.notificationService = notificationService;
        this.userDetailsService = userDetailsService;
        this.branchService = branchService;
    }

    private final AuditAttributeServiceSecurity auditAttributeServiceSecurity;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;
    private final AccessService accessService;
    private final UserRolService userRolService;
    private final UserAccessService userAccessService;
    private final EventPublisher eventPublisher;
    private final UserOP outputPort;
    private final UserDM domainMapper;
    private final NotificationService notificationService;
    private final UserDetailsServiceImpl userDetailsService;
    private final BranchService branchService;
}