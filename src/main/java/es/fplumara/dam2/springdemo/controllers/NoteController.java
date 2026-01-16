package es.fplumara.dam2.springdemo.controllers;

import es.fplumara.dam2.springdemo.domain.Note;
import es.fplumara.dam2.springdemo.dto.CreateNoteRequest;
import es.fplumara.dam2.springdemo.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Note> create(@Valid @RequestBody CreateNoteRequest req) {
        Note saved = noteService.createNoteWithAudit(req.text());
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Note> all() {
        return noteService.findAll();
    }
}
