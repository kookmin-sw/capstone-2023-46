package com.capstone.capstone.controller;

import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PictureController {

    @PostMapping("/picture")
    public void postPicture(@AuthenticationPrincipal UserDetailsImpl userDetails){

    }

    @DeleteMapping("/picture")
    public void delPicture(@AuthenticationPrincipal UserDetailsImpl userDetails){

    }
}
