package es.fplumara.dam2.springdemo.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Instant createdAt;

    protected Note() { }

    public Note(String text) {
        this.text = text;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public Instant getCreatedAt() { return createdAt; }

    public void setText(String text) { this.text = text; }
}

