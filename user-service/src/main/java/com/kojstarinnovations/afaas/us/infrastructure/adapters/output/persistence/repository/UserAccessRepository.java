package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository;

import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Integer>{

    void deleteByUserId(String userId);

    boolean existsByUserIdAndAccessId(String userId, int accessId);
}
