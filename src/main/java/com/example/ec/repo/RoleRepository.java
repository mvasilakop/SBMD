package com.example.ec.repo;

import com.example.ec.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by Michail Vasilakopoulos.
 */
@RepositoryRestResource(exported = true)
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRolename(String roleName);
}