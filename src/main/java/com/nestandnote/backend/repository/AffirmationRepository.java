package com.nestandnote.backend.repository;

import com.nestandnote.backend.entity.Affirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffirmationRepository extends JpaRepository<Affirmation, Long> {

    List<Affirmation> findByUserId(Long userId);
}