package com.nestandnote.backend.controller;

import com.nestandnote.backend.entity.BudgetEntry;
import com.nestandnote.backend.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public List<BudgetEntry> getEntries(@RequestParam("userId") Long userId) {
        return budgetService.getEntries(userId);
    }

    @PostMapping
    public BudgetEntry add(@RequestBody BudgetEntry entry) {
        return budgetService.add(entry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        budgetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}