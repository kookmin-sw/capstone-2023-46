package com.capstone.capstone.dto.ResponseDto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Builder
public class PictureResponseDto {

    private Long picture_id;

    private LocalDate date;

    private String imgUrls;
}
