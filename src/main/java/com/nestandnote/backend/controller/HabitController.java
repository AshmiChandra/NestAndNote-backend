package com.nestandnote.backend.controller;

import com.nestandnote.backend.dto.HabitDtos.HabitResponse;
import com.nestandnote.backend.entity.Habit;
import com.nestandnote.backend.entity.WaterLog;
import com.nestandnote.backend.service.HabitService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping("/habits")
    public List<HabitResponse> getHabits(@RequestParam("userId") Long userId) {
        return habitService.getHabits(userId);
    }

    @PostMapping("/habits")
    public HabitResponse addHabit(@RequestBody Habit habit) {
        return habitService.addHabit(habit);
    }

    @DeleteMapping("/habits/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable("id") Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/habits/{id}/toggle")
    public ResponseEntity<Void> toggleDay(
            @PathVariable("id") Long id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        habitService.toggleDay(id, date);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/water")
    public WaterLog getWater(
            @RequestParam("userId") Long userId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return habitService.getWater(userId, date);
    }

    @PutMapping("/water")
    public WaterLog saveWater(@RequestBody WaterLog waterLog) {
        return habitService.saveWater(waterLog);
    }
}