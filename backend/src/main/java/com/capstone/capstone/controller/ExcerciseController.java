package com.capstone.capstone.controller;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ExcerciseController {
    private final ExerciseService exerciseService;
    @PostMapping("/exercise")
    public String testGet(@RequestBody ExerciseRequestDto exerciseRequestDto){
        exerciseService.saveExercise(exerciseRequestDto);
        return "done";
    }
}
