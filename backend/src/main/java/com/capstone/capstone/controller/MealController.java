package com.capstone.capstone.controller;

import com.capstone.capstone.dto.MealRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping("/meal")
    public ResponseEntity postMeal(@RequestBody MealRequestDto mealRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        mealService.saveMeal(mealRequestDto, userDetails);
        return ResponseEntity.ok().body("저장 완료");
    }

    @DeleteMapping("/meal/{id}")
    public ResponseEntity delMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        mealService.deleteMeal(userDetails, id);
        return ResponseEntity.ok().body("삭제 완료");
    }

    @PutMapping("/meal/{id}")
    public ResponseEntity putMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id,
                                      @RequestBody MealRequestDto mealRequestDto){
        return mealService.editMeal(userDetails, id, mealRequestDto);
    }

    //date sring으로 받은 뒤 localdate로 변환
    @GetMapping("/meal/{date}")
    public ResponseEntity getMeal(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String date){
        return mealService.getMeal(userDetails, date);
    }
}
