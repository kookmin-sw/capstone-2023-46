package com.capstone.capstone.controller;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.model.Calendar;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/calendar")
    public ResponseEntity postExercise(@AuthenticationPrincipal UserDetailsImpl userDetails){
        calendarService.saveCalendar(userDetails);
        return ResponseEntity.ok().body("저장 완료");
    }


    //calendar에 get 하는 건 그 달치 calendar를 get을 하도록 혹은 이전 달의 calendar를 get 하도록
    // calendar가 내려줄 값은 각 요일의 총 칼로리 및 무게만
    @GetMapping("/calendar")
    public ResponseEntity getCalendar(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return calendarService.findCalendarMonth(userDetails);
    }
}
