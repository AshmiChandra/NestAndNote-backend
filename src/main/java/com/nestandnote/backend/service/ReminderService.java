package com.nestandnote.backend.service;

import com.nestandnote.backend.entity.Reminder;
import com.nestandnote.backend.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepo;

    public ReminderService(ReminderRepository reminderRepo) {
        this.reminderRepo = reminderRepo;
    }

    public List<Reminder> getReminders(Long userId) {
        return reminderRepo.findByUserIdOrderByRemindAtAsc(userId);
    }

    public Reminder add(Reminder reminder) {
        if (reminder.getTitle() == null || reminder.getTitle().isBlank()) {
            throw new IllegalArgumentException("Reminder title cannot be empty");
        }
        if (reminder.getRemindAt() == null) {
            throw new IllegalArgumentException("Reminder time is required");
        }
        reminder.setId(null);
        reminder.setDone(false);
        return reminderRepo.save(reminder);
    }

    public Reminder toggleDone(Long id) {
        Reminder r = reminderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reminder not found"));
        r.setDone(!r.isDone());
        return reminderRepo.save(r);
    }

    public void delete(Long id) {
        reminderRepo.deleteById(id);
    }
}