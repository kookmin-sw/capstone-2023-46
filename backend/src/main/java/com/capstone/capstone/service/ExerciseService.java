package com.capstone.capstone.service;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.Exercise;
import com.capstone.capstone.repository.ExerciseRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public void saveExercise(ExerciseRequestDto exerciseRequestDto, UserDetailsImpl userDetails){
        Exercise exercise = new Exercise(exerciseRequestDto, userDetails);
        exerciseRepository.save(exercise);
    }

    @Transactional
    public void deleteExercise(UserDetailsImpl userDetails, Long id){
        Optional<Exercise> exercise = exerciseRepository.findExerciseById(id);
        if (!exercise.get().getUserNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        exerciseRepository.delete(exercise.get());
    }

    @Transactional
    public ResponseEntity editExercise(UserDetailsImpl userDetails, Long id, ExerciseRequestDto exerciseRequestDto){
        Optional<Exercise> exercise = exerciseRepository.findExerciseById(id);
        if (!exercise.get().getUserNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        exercise.get().setExercise(exerciseRequestDto);

        return ResponseEntity.ok().body(exercise.get());
    }

    @Transactional
    public ResponseEntity getExercise(UserDetailsImpl userDetails, String date){
        List<Exercise> exerciseList = exerciseRepository.findAllByDateAndUserNickname(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), userDetails.getUser().getNickname());
        if (!exerciseList.get(0).getUserNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        return ResponseEntity.ok().body(exerciseList);

    }
}
