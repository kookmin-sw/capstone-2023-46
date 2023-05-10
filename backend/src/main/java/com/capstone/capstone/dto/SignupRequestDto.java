package com.capstone.capstone.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    private String username;

    private String nickname;

    private String password;
}