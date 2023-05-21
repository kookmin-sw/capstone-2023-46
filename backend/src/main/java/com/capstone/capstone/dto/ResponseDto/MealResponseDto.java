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
public class MealResponseDto {

    private Long meal_id;

    private LocalDate date;

    private Long protein;
    private Long fat;
    private Long calorie;
    private Long carbohydrate;
}
