package com.nestandnote.backend.service;

import com.nestandnote.backend.entity.BudgetEntry;
import com.nestandnote.backend.repository.BudgetEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetService {

    private final BudgetEntryRepository entryRepo;

    public BudgetService(BudgetEntryRepository entryRepo) {
        this.entryRepo = entryRepo;
    }

    public List<BudgetEntry> getEntries(Long userId) {
        return entryRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public BudgetEntry add(BudgetEntry entry) {
        if (entry.getTitle() == null || entry.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (entry.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be more than 0");
        }
        if (!"income".equals(entry.getType()) && !"expense".equals(entry.getType())) {
            throw new IllegalArgumentException("Type must be income or expense");
        }
        entry.setId(null);
        entry.setCreatedAt(LocalDateTime.now());
        return entryRepo.save(entry);
    }

    public void delete(Long id) {
        entryRepo.deleteById(id);
    }
}