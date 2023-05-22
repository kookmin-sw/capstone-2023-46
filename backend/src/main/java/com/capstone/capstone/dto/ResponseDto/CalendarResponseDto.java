package com.capstone.capstone.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDto {
    private Long calendar_id;

    private LocalDate date;

    private Long dayCalorie;

    private Long dayWeight;

    private boolean hasMeal;

    private boolean hasExercise;
}
