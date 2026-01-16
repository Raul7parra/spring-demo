package es.fplumara.dam2.springdemo.services;

import es.fplumara.dam2.springdemo.domain.AuditLog;
import es.fplumara.dam2.springdemo.domain.Note;
import es.fplumara.dam2.springdemo.repositories.AuditLogRepository;
import es.fplumara.dam2.springdemo.repositories.NoteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepo;
    private final AuditLogRepository auditRepo;

    public NoteService(NoteRepository noteRepo, AuditLogRepository auditRepo) {
        this.noteRepo = noteRepo;
        this.auditRepo = auditRepo;
    }

    @Transactional
    public Note createNoteWithAudit(String text) {
        Note saved = noteRepo.save(new Note(text));

        // Simulamos un fallo si el texto contiene "FAIL"
        if (text.toUpperCase().contains("FAIL")) {
            throw new RuntimeException("Fallo simulado después de guardar la Note");
        }

        auditRepo.save(new AuditLog("Created note id=" + saved.getId()));
        return saved;
    }

    public List<Note> findAll() {
        return noteRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
