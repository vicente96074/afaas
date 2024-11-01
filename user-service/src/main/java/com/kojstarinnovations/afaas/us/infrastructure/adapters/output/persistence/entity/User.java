package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity;

import com.kojstarinnovations.afaas.commons.data.helper.UUIDHelper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * User Entity Class Definition - JPA to represent the user table in the database
 *
 * @author Augusto Vicente
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AuditAttributeSecurity {

    @Id
    private String id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "person_identification", unique = true, nullable = false)
    private String identification;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "store_branch_id")
    private String storeBranchId;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "url_profile_picture")
    private String urlProfilePicture;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_access",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "access_id")
    )
    private Set<Access> accesses = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = UUIDHelper.generateUUID("US", 8);
        }
    }
}