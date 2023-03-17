package com.capstone.capstone.controller;

import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class testController {
    @GetMapping("/test")
    public static String testGet(UserDetailsImpl userDetails){
        System.out.println(userDetails.getUsername());
        return "done";
    }
}
