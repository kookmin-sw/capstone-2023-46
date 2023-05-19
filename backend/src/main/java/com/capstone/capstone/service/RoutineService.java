package com.capstone.capstone.service;

import com.capstone.capstone.dto.R_ExerciseRequestDto;
import com.capstone.capstone.dto.RoutineRequestDto;
import com.capstone.capstone.model.Routine;
import com.capstone.capstone.model.Routine_Exercise;
import com.capstone.capstone.repository.R_ExerciseRepository;
import com.capstone.capstone.repository.RoutineRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final R_ExerciseRepository rExerciseRepository;

    public void saveRoutine(UserDetailsImpl userDetails, List<R_ExerciseRequestDto> rExerciseRequestDtos, RoutineRequestDto routineRequestDto){
        Routine routine = Routine.builder()
                .routine_name(routineRequestDto.getRoutine_name())
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

    public ResponseEntity findAllRoutines(UserDetailsImpl userDetails){
        List<Routine> routines = routineRepository.findAllByUser(userDetails.getUser());

        return ResponseEntity.ok().body(routines);
    }

    public ResponseEntity findRoutine(UserDetailsImpl userDetails, Long routine_id){
        List<Routine_Exercise> routine = rExerciseRepository.findAllByRoutine_RoutineId(routine_id);

        return ResponseEntity.ok().body(routine);
    }

}
