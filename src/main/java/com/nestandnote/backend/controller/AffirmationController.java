package com.nestandnote.backend.controller;

import com.nestandnote.backend.entity.Affirmation;
import com.nestandnote.backend.service.AffirmationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affirmations")
public class AffirmationController {

    private final AffirmationService affirmationService;

    public AffirmationController(AffirmationService affirmationService) {
        this.affirmationService = affirmationService;
    }

    @GetMapping
    public List<Affirmation> getAffirmations(@RequestParam("userId") Long userId) {
        return affirmationService.getAffirmations(userId);
    }

    @PostMapping
    public Affirmation add(@RequestBody Affirmation affirmation) {
        return affirmationService.add(affirmation);
    }

    @PutMapping("/{id}/favorite")
    public Affirmation toggleFavorite(@PathVariable("id") Long id) {
        return affirmationService.toggleFavorite(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        affirmationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}