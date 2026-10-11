package com.nestandnote.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chant_sessions")
@Getter
@Setter
@NoArgsConstructor
public class ChantSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String mantra;

    private int repeatCount;

    private int target;

    private LocalDateTime completedAt = LocalDateTime.now();
}