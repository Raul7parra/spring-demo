package es.fplumara.dam2.springdemo.repositories;

import es.fplumara.dam2.springdemo.domain.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> { }
