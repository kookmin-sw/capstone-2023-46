package com.capstone.capstone.service;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.model.Exercise;
import com.capstone.capstone.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public void saveExercise(ExerciseRequestDto exerciseRequestDto){
        Exercise exercise = new Exercise(exerciseRequestDto);
        exerciseRepository.save(exercise);
    }
}
