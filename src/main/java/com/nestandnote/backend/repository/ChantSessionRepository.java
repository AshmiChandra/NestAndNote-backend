package com.nestandnote.backend.repository;

import com.nestandnote.backend.entity.ChantSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChantSessionRepository extends JpaRepository<ChantSession, Long> {

    List<ChantSession> findByUserIdOrderByCompletedAtDesc(Long userId);
}