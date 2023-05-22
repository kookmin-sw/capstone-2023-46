package com.capstone.capstone.service;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.dto.ResponseDto.ExerciseResponseDto;
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
import java.util.ArrayList;
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
                        .weights(exerciseRequestDto.getWeights())
                        .build();

        // 당일 총 운동량 더해주기
        calendar.setDayWeight(exercise.getExerciseWeight());
        // has exercise true로
        calendar.setHasExercise(true);

        exerciseRepository.save(exercise);
    }

    @Transactional
    public void deleteExercise(UserDetailsImpl userDetails, Long id){
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.INVALID_EXERCISE_ID)
        );
        
        if (!exercise.getCalendar().getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        // 당일 총 운동량 빼주기
        exercise.getCalendar().setDayWeight(-(exercise.getExerciseWeight()));

        // 삭제할 때 hasExercise 체크
        if(exercise.getCalendar().getDayWeight() == 0);
            exercise.getCalendar().setHasExercise(false);

        exerciseRepository.delete(exercise);
    }

    public ResponseEntity editExercise(UserDetailsImpl userDetails, Long id, ExerciseRequestDto exerciseRequestDto){
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.EXERCISE_NOT_FOUND)
        );
        // exercise 있는지

        if (!exercise.getCalendar().getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        exercise.setExercise(exerciseRequestDto);
        exerciseRepository.save(exercise);

        ExerciseResponseDto exerciseResponseDto = ExerciseResponseDto.builder()
                .exercise_id(exercise.getExercise_id())
                .date(exercise.getDate())
                .name(exercise.getName())
                .set(exercise.getSet())
                .weight(exercise.getWeights())
                .build();
        return ResponseEntity.ok().body(exerciseResponseDto);
    }

    @Transactional
    public ResponseEntity getExercise(UserDetailsImpl userDetails, String date, Long calendar_id){
        List<Exercise> exerciseList = exerciseRepository.findAllByDateAndCalendar_CalendarId(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), calendar_id);
        List<ExerciseResponseDto> responseDtos = new ArrayList<>();

        // exercise 없는 경우
        if(exerciseList.isEmpty())
            throw new CustomException(ErrorCode.EXERCISE_NOT_FOUND);

        // calendar user와 접속 user 닉네임이 다른 경우
        if (!exerciseList.get(0).getCalendar().getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        for(Exercise exercise : exerciseList){
            responseDtos.add(new ExerciseResponseDto().builder()
                            .exercise_id(exercise.getExercise_id())
                            .date(exercise.getDate())
                            .name(exercise.getName())
                            .set(exercise.getSet())
                            .weight(exercise.getWeights())
                    .build());
        }

        return ResponseEntity.ok().body(responseDtos);
    }
}
