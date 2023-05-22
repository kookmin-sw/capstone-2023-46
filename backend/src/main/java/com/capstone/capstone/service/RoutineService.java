package com.capstone.capstone.service;

import com.capstone.capstone.dto.R_ExerciseRequestDto;
import com.capstone.capstone.dto.ResponseDto.RoutineDetailResponseDto;
import com.capstone.capstone.dto.ResponseDto.RoutineResponseDto;
import com.capstone.capstone.dto.RoutineRequestDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.Routine;
import com.capstone.capstone.model.Routine_Exercise;
import com.capstone.capstone.repository.R_ExerciseRepository;
import com.capstone.capstone.repository.RoutineRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.reducing;

@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final R_ExerciseRepository rExerciseRepository;

    public void saveRoutine(UserDetailsImpl userDetails, List<R_ExerciseRequestDto> rExerciseRequestDtos, RoutineRequestDto routineRequestDto){
        Routine routine = Routine.builder()
                .routine_name(routineRequestDto.getRoutine_name())
                .total_set(rExerciseRequestDtos.stream()
                        .map(R_ExerciseRequestDto::getSet)
                        .collect(reducing(Long::sum)).get())
                .user(userDetails.getUser())
                .build();

        routineRepository.save(routine);

        for(R_ExerciseRequestDto rExerciseRequestDto : rExerciseRequestDtos){
            Routine_Exercise routineExercise = Routine_Exercise.builder()
                    .name(rExerciseRequestDto.getExercise_name())
                    .set(rExerciseRequestDto.getSet())
                    .routine(routine)
                    .build();

            rExerciseRepository.save(routineExercise);
        }
    }

    public ResponseEntity editRoutine(UserDetailsImpl userDetails, Long routine_id,
                                      List<R_ExerciseRequestDto> rExerciseRequestDtos, RoutineRequestDto routineRequestDto){
        Routine routine = routineRepository.findById(routine_id).orElseThrow(
                () -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND)
        );

        if (!routine.getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_ROUTINE_ID);

        routine.editRoutine(routineRequestDto, rExerciseRequestDtos);
        routineRepository.save(routine);

        for(Routine_Exercise routineExercise : routine.getR_exercises()){
            routineExercise.setRoutine(routine);
            rExerciseRepository.save(routineExercise);
        }

        return ResponseEntity.ok().body("");
    }

    public ResponseEntity delRoutine(UserDetailsImpl userDetails, Long routine_id){
        Routine routine = routineRepository.findById(routine_id).orElseThrow(
                () -> new CustomException(ErrorCode.ROUTINE_NOT_FOUND)
        );

        if (!routine.getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_ROUTINE_ID);

        routineRepository.delete(routine);

        return ResponseEntity.ok().body("");
    }

    public ResponseEntity findAllRoutines(UserDetailsImpl userDetails){
        List<Routine> routines = routineRepository.findAllByUser(userDetails.getUser());
        List<RoutineResponseDto> responseDtos = new ArrayList<>();

        for(Routine routine : routines){
            RoutineResponseDto routineResponseDto = RoutineResponseDto.builder()
                    .routine_id(routine.getRoutineId())
                    .routine_name(routine.getRoutine_name())
                    .total_set(routine.getTotal_set())
                    .build();

            responseDtos.add(routineResponseDto);
        }

        return ResponseEntity.ok().body(responseDtos);
    }

    public ResponseEntity findRoutine(UserDetailsImpl userDetails, Long routine_id){
        List<Routine_Exercise> routine = rExerciseRepository.findAllByRoutine_RoutineId(routine_id);
        List<RoutineDetailResponseDto> responseDtos = new ArrayList<>();

        if(routine.isEmpty())
            throw new CustomException(ErrorCode.ROUTINE_NOT_FOUND);

        if(!routine.get(0).getRoutine().getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_ROUTINE_ID);

        for(Routine_Exercise routineExercise : routine){
            RoutineDetailResponseDto responseDto = RoutineDetailResponseDto.builder()
                    .name(routineExercise.getName())
                    .set(routineExercise.getSet())
                    .build();

            responseDtos.add(responseDto);
        }

        return ResponseEntity.ok().body(responseDtos);
    }

}
