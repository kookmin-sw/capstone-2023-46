package com.capstone.capstone.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExerciseRequestDto {
    private String name;
    private Long set;
    private List<Long> weights;
}
