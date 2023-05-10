package com.capstone.capstone.controller;

import com.capstone.capstone.dto.RoutineRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoutineController {

    @PostMapping("/routine")
    public void postRoutine(@AuthenticationPrincipal UserDetailsImpl userDetails, RoutineRequestDto routineRequestDto){

    }

    @GetMapping("/routine")
    public void getRoutine(@AuthenticationPrincipal UserDetailsImpl userDetails){

    }

    @DeleteMapping("/routine")
    public void delRoutine(@AuthenticationPrincipal UserDetailsImpl userDetails){

    }
}
