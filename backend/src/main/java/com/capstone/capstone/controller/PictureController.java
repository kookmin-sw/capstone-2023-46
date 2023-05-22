package com.capstone.capstone.controller;

import com.capstone.capstone.dto.PhotoDto;
import com.capstone.capstone.security.UserDetailsImpl;
import com.capstone.capstone.service.PictureService;
import com.capstone.capstone.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PictureController {

    private final S3Service s3Service;
    private final PictureService pictureService;

    @PostMapping("/calendar/picture")
    public ResponseEntity postPicture(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @RequestPart("imgUrl") List<MultipartFile> multipartFiles){
        List<PhotoDto> photoDtos = s3Service.uploadFile(multipartFiles);

        return pictureService.savePictures(userDetails, photoDtos);
    }

    @DeleteMapping("/calendar/picture/{picture_id}")
    public ResponseEntity delPicture(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long picture_id){
        return pictureService.delPicture(userDetails, picture_id);
    }

    @GetMapping("/calendar/{calendar_id}/picture/{date}")
    public ResponseEntity getPicture(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String date, @PathVariable Long calendar_id){
        return pictureService.getPicture(userDetails, date, calendar_id);
    }
}
