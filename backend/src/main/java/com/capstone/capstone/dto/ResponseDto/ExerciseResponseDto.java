package com.capstone.capstone.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponseDto {

    private Long exercise_id;

    private LocalDate date;

    private String name;

    private Long set;

    private List<Long> weight;
}
