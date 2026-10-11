package com.nestandnote.backend.service;

import com.nestandnote.backend.dto.HabitDtos.HabitResponse;
import com.nestandnote.backend.entity.Habit;
import com.nestandnote.backend.entity.HabitLog;
import com.nestandnote.backend.entity.WaterLog;
import com.nestandnote.backend.repository.HabitLogRepository;
import com.nestandnote.backend.repository.HabitRepository;
import com.nestandnote.backend.repository.WaterLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    private final HabitRepository habitRepo;
    private final HabitLogRepository logRepo;
    private final WaterLogRepository waterRepo;

    public HabitService(HabitRepository habitRepo, HabitLogRepository logRepo, WaterLogRepository waterRepo) {
        this.habitRepo = habitRepo;
        this.logRepo = logRepo;
        this.waterRepo = waterRepo;
    }

    public List<HabitResponse> getHabits(Long userId) {
        return habitRepo.findByUserId(userId).stream()
                .map(h -> new HabitResponse(
                        h.getId(),
                        h.getName(),
                        logRepo.findByHabitId(h.getId()).stream()
                                .map(HabitLog::getLogDate)
                                .toList()))
                .toList();
    }

    public HabitResponse addHabit(Habit habit) {
        if (habit.getName() == null || habit.getName().isBlank()) {
            throw new IllegalArgumentException("Habit name cannot be empty");
        }
        habit.setId(null);
        Habit saved = habitRepo.save(habit);
        return new HabitResponse(saved.getId(), saved.getName(), List.of());
    }

    @Transactional
    public void deleteHabit(Long id) {
        logRepo.deleteByHabitId(id);
        habitRepo.deleteById(id);
    }

    // tick or untick a habit for one date
    public void toggleDay(Long habitId, LocalDate date) {
        Optional<HabitLog> existing = logRepo.findByHabitIdAndLogDate(habitId, date);
        if (existing.isPresent()) {
            logRepo.delete(existing.get());
        } else {
            HabitLog log = new HabitLog();
            log.setHabitId(habitId);
            log.setLogDate(date);
            logRepo.save(log);
        }
    }

    public WaterLog getWater(Long userId, LocalDate date) {
        return waterRepo.findByUserIdAndLogDate(userId, date).orElseGet(() -> {
            WaterLog w = new WaterLog();
            w.setUserId(userId);
            w.setLogDate(date);
            w.setGlasses(0);
            w.setGoal(8);
            return w;
        });
    }

    public WaterLog saveWater(WaterLog body) {
        WaterLog w = waterRepo.findByUserIdAndLogDate(body.getUserId(), body.getLogDate())
                .orElse(new WaterLog());
        w.setUserId(body.getUserId());
        w.setLogDate(body.getLogDate());
        w.setGlasses(body.getGlasses());
        w.setGoal(body.getGoal());
        return waterRepo.save(w);
    }
}