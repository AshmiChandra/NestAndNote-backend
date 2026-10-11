package com.nestandnote.backend.service;

import com.nestandnote.backend.entity.Note;
import com.nestandnote.backend.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepo;

    public NoteService(NoteRepository noteRepo) {
        this.noteRepo = noteRepo;
    }

    public List<Note> getNotes(Long userId) {
        return noteRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Note create(Note note) {
        if (note.getText() == null || note.getText().isBlank()) {
            throw new IllegalArgumentException("Note cannot be empty");
        }
        note.setId(null);
        note.setCreatedAt(LocalDateTime.now());
        return noteRepo.save(note);
    }

    public Note update(Long id, Note body) {
        Note note = noteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
        note.setText(body.getText());
        return noteRepo.save(note);
    }

    public void delete(Long id) {
        noteRepo.deleteById(id);
    }
}