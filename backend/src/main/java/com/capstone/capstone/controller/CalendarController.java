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

    @GetMapping("/calendar")
    public Calendar getCalendar(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return calendarService.findCalendar(userDetails);
    }
}
