package com.nestandnote.backend.controller;

import com.nestandnote.backend.entity.Reminder;
import com.nestandnote.backend.service.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping
    public List<Reminder> getReminders(@RequestParam("userId") Long userId) {
        return reminderService.getReminders(userId);
    }

    @PostMapping
    public Reminder add(@RequestBody Reminder reminder) {
        return reminderService.add(reminder);
    }

    @PutMapping("/{id}/toggle")
    public Reminder toggleDone(@PathVariable("id") Long id) {
        return reminderService.toggleDone(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reminderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}