package com.capstone.capstone.dto.ResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RoutineDetailResponseDto {
    private String name;

    private Long set;
}
