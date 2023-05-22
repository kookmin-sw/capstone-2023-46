package com.capstone.capstone.controller;

import com.capstone.capstone.dto.R_ExerciseRequestDto;
import com.capstone.capstone.dto.RoutineRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping("/routine")
    public void postRoutine(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart("exercise") List<R_ExerciseRequestDto> rExerciseRequestDtos,
                            @RequestPart("routine") RoutineRequestDto routineRequestDto){
        routineService.saveRoutine(userDetails, rExerciseRequestDtos, routineRequestDto);
    }

    @GetMapping("/routine")
    public ResponseEntity getAllRoutines(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return routineService.findAllRoutines(userDetails);
    }

    @GetMapping("/routine/{routine_id}")
    public ResponseEntity getRoutineDetail(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long routine_id){
        return routineService.findRoutine(userDetails, routine_id);
    }

    @DeleteMapping("/routine/{routine_id}")
    public ResponseEntity delRoutine(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long routine_id){
        return routineService.delRoutine(userDetails, routine_id);
    }

    @PutMapping("/routine/{routine_id}")
    public ResponseEntity putRoutine(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart("exercise") List<R_ExerciseRequestDto> rExerciseRequestDtos,
                                     @RequestPart("routine") RoutineRequestDto routineRequestDto, @PathVariable Long routine_id){
        return routineService.editRoutine(userDetails, routine_id, rExerciseRequestDtos, routineRequestDto);
    }
}
