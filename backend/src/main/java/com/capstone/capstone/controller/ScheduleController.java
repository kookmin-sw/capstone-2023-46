package com.capstone.capstone.controller;

import com.capstone.capstone.dto.ScheduleRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 해당 주차 스케쥴 저장
    @PostMapping("/schedule")
    public ResponseEntity postSchedule(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ScheduleRequestDto scheduleRequestDto){
        scheduleService.saveSchedule(userDetails, scheduleRequestDto);
        return ResponseEntity.ok().body("저장 완료");
    }

    // 스케쥴 인증
    @PatchMapping("/schedule/checkIn")
    public ResponseEntity patchCheckIn(@AuthenticationPrincipal UserDetailsImpl userDetaill){

        return ResponseEntity.ok().body("");
    }

    // 스케쥴 수정
    @PatchMapping("/schedule")
    public ResponseEntity patchSchedule(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ScheduleRequestDto scheduleRequestDto){
        return scheduleService.editSchedule(userDetails, scheduleRequestDto);
    }
}
