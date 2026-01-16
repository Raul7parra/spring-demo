package es.fplumara.dam2.springdemo.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected AuditLog() { }

    public AuditLog(String action) {
        this.action = action;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getAction() { return action; }
    public Instant getCreatedAt() { return createdAt; }
}
