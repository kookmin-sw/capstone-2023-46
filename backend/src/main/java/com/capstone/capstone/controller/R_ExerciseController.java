package com.capstone.capstone.controller;

import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

 // R_exercise Controller가 필요한가?

@RestController
@RequiredArgsConstructor
public class R_ExerciseController {

    @PostMapping("/rexercise")
    public void postRexercise(@AuthenticationPrincipal UserDetailsImpl userDetails){

    }

    @GetMapping("/rexercise")
    public void getRexercise(@AuthenticationPrincipal UserDetailsImpl userDetails){

    }

    @DeleteMapping("/rexercise")
    public void delRexercise(@AuthenticationPrincipal UserDetailsImpl userDetails){

    }
}
