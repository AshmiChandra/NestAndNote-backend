package com.nestandnote.backend.repository;

import com.nestandnote.backend.entity.Mantra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MantraRepository extends JpaRepository<Mantra, Long> {

    List<Mantra> findByUserId(Long userId);
}