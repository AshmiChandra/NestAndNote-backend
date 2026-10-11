package com.nestandnote.backend.service;

import com.nestandnote.backend.entity.Affirmation;
import com.nestandnote.backend.repository.AffirmationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffirmationService {

    private final AffirmationRepository affirmationRepo;

    public AffirmationService(AffirmationRepository affirmationRepo) {
        this.affirmationRepo = affirmationRepo;
    }

    public List<Affirmation> getAffirmations(Long userId) {
        return affirmationRepo.findByUserId(userId);
    }

    public Affirmation add(Affirmation affirmation) {
        if (affirmation.getText() == null || affirmation.getText().isBlank()) {
            throw new IllegalArgumentException("Affirmation cannot be empty");
        }
        affirmation.setId(null);
        return affirmationRepo.save(affirmation);
    }

    public Affirmation toggleFavorite(Long id) {
        Affirmation a = affirmationRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Affirmation not found"));
        a.setFavorite(!a.isFavorite());
        return affirmationRepo.save(a);
    }

    public void delete(Long id) {
        affirmationRepo.deleteById(id);
    }
}