package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository;

import com.kojstarinnovations.afaas.commons.emuns.RolName;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is used to access the database and perform CRUD operations on the rol table
 *
 * @author Augusto Vicente
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    /**
     * This method is used to find a rol by its name
     *
     * @param rolName The name of the rol
     * @return An optional of the rol
     */
    Optional<Rol> findByRolName(RolName rolName);

    /**
     * This method is used to check if a rol exists by its name
     *
     * @param rolName The name of the rol
     * @return A boolean indicating if the rol exists
     */
    boolean existsByRolName(RolName rolName);
}
