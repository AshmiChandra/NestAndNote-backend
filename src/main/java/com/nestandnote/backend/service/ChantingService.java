package com.nestandnote.backend.service;

import com.nestandnote.backend.entity.ChantSession;
import com.nestandnote.backend.entity.Mantra;
import com.nestandnote.backend.repository.ChantSessionRepository;
import com.nestandnote.backend.repository.MantraRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChantingService {

    private final MantraRepository mantraRepo;
    private final ChantSessionRepository sessionRepo;

    public ChantingService(MantraRepository mantraRepo, ChantSessionRepository sessionRepo) {
        this.mantraRepo = mantraRepo;
        this.sessionRepo = sessionRepo;
    }

    public List<Mantra> getMantras(Long userId) {
        return mantraRepo.findByUserId(userId);
    }

    public Mantra addMantra(Mantra mantra) {
        if (mantra.getText() == null || mantra.getText().isBlank()) {
            throw new IllegalArgumentException("Mantra cannot be empty");
        }
        mantra.setId(null);
        return mantraRepo.save(mantra);
    }

    public void deleteMantra(Long id) {
        mantraRepo.deleteById(id);
    }

    public List<ChantSession> getSessions(Long userId) {
        return sessionRepo.findByUserIdOrderByCompletedAtDesc(userId);
    }

    public ChantSession addSession(ChantSession session) {
        session.setId(null);
        session.setCompletedAt(LocalDateTime.now());
        return sessionRepo.save(session);
    }
}