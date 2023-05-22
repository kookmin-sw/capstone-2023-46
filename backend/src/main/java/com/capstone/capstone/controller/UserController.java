package com.capstone.capstone.controller;
import com.capstone.capstone.dto.AddressRequestDto;
import com.capstone.capstone.dto.SignupRequestDto;
import com.capstone.capstone.dto.PhoneNumbersRequestDto;
import com.capstone.capstone.model.User;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //회원가입
    @PostMapping("/api/signup")
    public ResponseEntity signupUser(@RequestBody SignupRequestDto requestDto) {
        return userService.signupUser(requestDto);
    }

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
    
    // 헬스장 등록 및 수정

    @PatchMapping("/user/address")
    public ResponseEntity patchAddress(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody AddressRequestDto addressRequestDto){
        return userService.editAddress(userDetails, addressRequestDto);
    }

    @GetMapping("/user/address")
    public ResponseEntity getAddress(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getAddress(userDetails);
    }

    @PatchMapping("/user/phone")
    public ResponseEntity patchPhoneNumbers(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PhoneNumbersRequestDto phoneNumbersRequestDto){
        return userService.editPhoneNumber(userDetails, phoneNumbersRequestDto);
    }

    @GetMapping("/userGet")
    public User get(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getUser(userDetails);
    }

}
