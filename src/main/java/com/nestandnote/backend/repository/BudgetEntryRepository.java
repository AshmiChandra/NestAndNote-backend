package com.nestandnote.backend.repository;

import com.nestandnote.backend.entity.BudgetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetEntryRepository extends JpaRepository<BudgetEntry, Long> {

    List<BudgetEntry> findByUserIdOrderByCreatedAtDesc(Long userId);
}