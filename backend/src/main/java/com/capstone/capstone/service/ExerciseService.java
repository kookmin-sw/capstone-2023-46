package com.capstone.capstone.service;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.Calendar;
import com.capstone.capstone.model.Exercise;
import com.capstone.capstone.repository.CalendarRepositoy;
import com.capstone.capstone.repository.ExerciseRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final CalendarRepositoy calendarRepositoy;

    @Transactional
    public void saveExercise(ExerciseRequestDto exerciseRequestDto, UserDetailsImpl userDetails){
        Calendar calendar = calendarRepositoy.findCalendarByUserAndDate(userDetails.getUser(), LocalDate.now()).get();
        Exercise exercise = Exercise.builder()
                        .name(exerciseRequestDto.getName())
                        .calendar(calendar)
                        .date(LocalDate.now())
                        .time(LocalTime.now())
                        .set(exerciseRequestDto.getSet())
                        .userNickname(userDetails.getUser().getNickname())
                        .build();
//        calendar.addExercise(exercise);
//        System.out.println(calendar.getExercises().get(0).getName());
//        System.out.println(calendar.getExercises().get(0).getExercise_id());
        exerciseRepository.save(exercise);
    }

    @Transactional
    public void deleteExercise(UserDetailsImpl userDetails, Long id){
        Optional<Calendar> calendar = calendarRepositoy.findCalendarByUserAndDate(userDetails.getUser(), LocalDate.now());
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (!exercise.get().getUserNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

//        System.out.println(calendar.get().getExercises().get(0).getName());
//        calendarRepositoy.save(calendar.get());
        exerciseRepository.delete(exercise.get());
        calendar.get().getExercises().remove(exercise.get());
    }

    @Transactional
    public ResponseEntity editExercise(UserDetailsImpl userDetails, Long id, ExerciseRequestDto exerciseRequestDto){
        Optional<Exercise> exercise = exerciseRepository.findById(id);
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
