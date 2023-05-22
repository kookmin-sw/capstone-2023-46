package com.capstone.capstone.dto.ResponseDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RoutineResponseDto {
    private Long routine_id;

    private String routine_name;

    private Long total_set;
}
