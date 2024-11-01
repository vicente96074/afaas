package com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.repository;

import com.kojstarinnovations.afaas.commons.emuns.AccessName;
import com.kojstarinnovations.afaas.us.infrastructure.adapters.output.persistence.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access, Integer>{

    Optional<Access> findByAccessName(AccessName accessName);

    boolean existsByAccessName(AccessName accessName);
}
