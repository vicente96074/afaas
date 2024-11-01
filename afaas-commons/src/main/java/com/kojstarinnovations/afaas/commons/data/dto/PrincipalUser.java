package com.kojstarinnovations.afaas.commons.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to create a PrincipalUser object that will be used to authenticate the user
 *
 * @author Augusto Vicente
 */
@AllArgsConstructor
@Data
public class PrincipalUser implements UserDetails {

    private String id;
    private String storeBranchId;
    private String storeId;
    private String credentials;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Collection<? extends GrantedAuthority> accesses;

    /**
     * This method is used to build a PrincipalUser object from a UserDTO object
     *
     * @param userDTO The user
     * @return A PrincipalUser object
     */
    public static PrincipalUser build(UserDTO userDTO) {

        List<GrantedAuthority> authorities =
                userDTO.getRolesDTO().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolName().name())).collect(Collectors.toList());

        List<GrantedAuthority> accesses =
                userDTO.getAccessesDTO().stream().map(access -> new SimpleGrantedAuthority(access.getAccessName().name())).collect(Collectors.toList());

        return new PrincipalUser(
                userDTO.getId(),
                userDTO.getStoreBranchId(),
                userDTO.getStoreId(),
                userDTO.getName() + " " + userDTO.getLastName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                authorities,
                accesses
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}