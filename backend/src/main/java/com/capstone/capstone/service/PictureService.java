package com.capstone.capstone.service;

import com.capstone.capstone.dto.PhotoDto;
import com.capstone.capstone.dto.ResponseDto.PictureResponseDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.Calendar;
import com.capstone.capstone.model.Picture;
import com.capstone.capstone.repository.CalendarRepositoy;
import com.capstone.capstone.repository.PictureRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;
    private final CalendarRepositoy calendarRepositoy;

    private final S3Service s3Service;

    public ResponseEntity savePictures(UserDetailsImpl userDetails, List<PhotoDto> photoDtos){
        Calendar calendar = calendarRepositoy.findCalendarByUserAndDate(userDetails.getUser(), LocalDate.now()).get();
        for(PhotoDto photoDto : photoDtos){
            Picture picture = Picture.builder()
                    .calendar(calendar)
                    .date(LocalDate.now())
                    .urls(photoDto.getPath())
                    .build();

            pictureRepository.save(picture);
        }



        return ResponseEntity.ok().body("저장완료");
    }

    public ResponseEntity getPicture(UserDetailsImpl userDetails, String date, Long calendar_id){
        List<Picture> pictures = pictureRepository.findAllByCalendar_CalendarIdAndDate(calendar_id, LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
        List<PictureResponseDto> pictureResponseDtos = new ArrayList<>();

        if(pictures.isEmpty())
            throw new CustomException(ErrorCode.EXERCISE_NOT_FOUND);


        if (!pictures.get(0).getCalendar().getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_PICTURE_ID);

        for(Picture picture : pictures){
            PictureResponseDto pictureResponseDto = PictureResponseDto.builder()
                    .picture_id(picture.getPicture_id())
                    .date(picture.getDate())
                    .imgUrls(picture.getUrls())
                    .build();

            pictureResponseDtos.add(pictureResponseDto);
        }
        return ResponseEntity.ok().body(pictureResponseDtos);
    }

    public ResponseEntity delPicture(UserDetailsImpl userDetails, Long id){
        Picture picture = pictureRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.PICTURE_NOT_FOUND)
        );

        if (!picture.getCalendar().getUser().getNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_PICTURE_ID);

        s3Service.deleteFile(picture.getUrls());
        return ResponseEntity.ok().body("");
    }
}
