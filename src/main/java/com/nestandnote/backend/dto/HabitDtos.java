package com.nestandnote.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class HabitDtos {

    // a habit together with the dates it was completed
    public record HabitResponse(Long id, String name, List<LocalDate> days) {}
}