package com.capstone.capstone.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PhoneNumbersRequestDto {
    private String phoneNumber;

    private List<String> yellowPage;
}
