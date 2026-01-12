/*
package es.fplumara.dam2.springdemo.controllers;

import es.fplumara.dam2.springdemo.domain.Note;
import es.fplumara.dam2.springdemo.dto.CreateNoteRequest;
import es.fplumara.dam2.springdemo.repositories.NoteRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository repo;

    public NoteController(NoteRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Note> create(@Valid @RequestBody CreateNoteRequest req) {
        Note saved = repo.save(new Note(req.text()));
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Note> all() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
