package com.nestandnote.backend.controller;

import com.nestandnote.backend.entity.ChantSession;
import com.nestandnote.backend.entity.Mantra;
import com.nestandnote.backend.service.ChantingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChantingController {

    private final ChantingService chantingService;

    public ChantingController(ChantingService chantingService) {
        this.chantingService = chantingService;
    }

    @GetMapping("/mantras")
    public List<Mantra> getMantras(@RequestParam("userId") Long userId) {
        return chantingService.getMantras(userId);
    }

    @PostMapping("/mantras")
    public Mantra addMantra(@RequestBody Mantra mantra) {
        return chantingService.addMantra(mantra);
    }

    @DeleteMapping("/mantras/{id}")
    public ResponseEntity<Void> deleteMantra(@PathVariable("id") Long id) {
        chantingService.deleteMantra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/chant-sessions")
    public List<ChantSession> getSessions(@RequestParam("userId") Long userId) {
        return chantingService.getSessions(userId);
    }

    @PostMapping("/chant-sessions")
    public ChantSession addSession(@RequestBody ChantSession session) {
        return chantingService.addSession(session);
    }
}