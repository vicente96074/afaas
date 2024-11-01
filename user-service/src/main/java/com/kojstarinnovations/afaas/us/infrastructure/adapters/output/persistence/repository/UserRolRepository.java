package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.UserRol;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.UserRolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
    
/**
 * UserRolRepository
 *
 * @author Augusto Vicente
 */
@Repository
public interface UserRolRepository extends JpaRepository<UserRol, UserRolId> {

    /**
     * deleteByUserId method
     *
     * @param userId the userId to be deleted
     */
    void deleteByUserId(String userId);
}
