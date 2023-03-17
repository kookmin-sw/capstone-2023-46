package com.capstone.capstone.controller;
import com.capstone.capstone.dto.SignupRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //회원가입
    @PostMapping("/api/signup")
    public ResponseEntity signupUser(@RequestBody SignupRequestDto requestDto) {
        return userService.signupUser(requestDto);
    }

    //회원가입에 이미지가 null이 들어올 때
//    @PostMapping("/api/user/signup")
//    public ResponseEntity signupNullUser(@RequestBody SignupImgRequestDto requestDto) {
//        return userService.signupNullUser(requestDto);
//    }

    //username 중복체크
    @PostMapping("/api/signup/checkID")
    public ResponseEntity checkUsername(@RequestBody SignupRequestDto requestDto) {
        return userService.checkUsername(requestDto);
    }

    //nickname 중복체크
    @PostMapping("/api/signup/nickID")
    public ResponseEntity checkNickname(@RequestBody SignupRequestDto requestDto) {
        return userService.checkNickname(requestDto);
    }

    //로그인 후 관리자 권한 얻을 수 있는 API


    //소셜로그인 사용자 정보 조회
//    @GetMapping("/social/user/islogin")
//    public ResponseEntity socialUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return userService.socialUserInfo(userDetails);
//    }

}
