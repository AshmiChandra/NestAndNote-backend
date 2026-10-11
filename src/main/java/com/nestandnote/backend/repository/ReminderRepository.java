package com.nestandnote.backend.repository;

import com.nestandnote.backend.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUserIdOrderByRemindAtAsc(Long userId);
}