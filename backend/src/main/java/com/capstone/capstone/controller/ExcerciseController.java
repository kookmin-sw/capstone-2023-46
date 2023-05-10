package com.capstone.capstone.controller;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.model.Exercise;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ExcerciseController {
    private final ExerciseService exerciseService;
    @PostMapping("/exercise")
    public ResponseEntity postExercise(@RequestBody ExerciseRequestDto exerciseRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(exerciseRequestDto.getName());
        exerciseService.saveExercise(exerciseRequestDto, userDetails);
        return ResponseEntity.ok().body("저장 완료");
    }

    @DeleteMapping("/exercise/{id}")
    public ResponseEntity delExercise(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        exerciseService.deleteExercise(userDetails, id);
        return ResponseEntity.ok().body("삭제 완료");
    }

    @PutMapping("/exercise/{id}")
    public ResponseEntity putExercise(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id,
                                      @RequestBody ExerciseRequestDto exerciseRequestDto){
        return exerciseService.editExercise(userDetails, id, exerciseRequestDto);
    }

    //date sring으로 받은 뒤 localdate로 변환
    @GetMapping("/exercise/{date}")
    public ResponseEntity getExercise(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String date){
        return exerciseService.getExercise(userDetails, date);
    }

}
