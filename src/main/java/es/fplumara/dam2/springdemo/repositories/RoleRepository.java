package es.fplumara.dam2.springdemo.repositories;

import es.fplumara.dam2.springdemo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}