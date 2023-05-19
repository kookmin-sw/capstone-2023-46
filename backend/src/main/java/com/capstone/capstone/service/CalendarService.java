package com.capstone.capstone.service;

import com.capstone.capstone.dto.ResponseDto.CalendarResponseDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.Calendar;
import com.capstone.capstone.repository.CalendarRepositoy;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import javax.xml.ws.Response;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepositoy calendarRepositoy;

    @Transactional
    public void saveCalendar(UserDetailsImpl userDetails){

        try {
            Optional<Calendar> calendar = calendarRepositoy.findCalendarByUserAndDate(userDetails.getUser(), LocalDate.now());
            Long id = calendar.get().getCalendar_id();
            // 만약 오늘의 calendar가 이미 존재한다면 error handler 실행
            throw new CustomException(ErrorCode.ALREADY_HAS_CALENDAR);
        } catch (NoSuchElementException e) {
            Calendar calendar = Calendar.builder()
                    .user(userDetails.getUser())
                    .date(LocalDate.now())
                    .hasMeal(false)
                    .hasExercise(false)
                    .dayCalorie(new Long(0))
                    .dayWeight(new Long(0))
                    .build();

            calendarRepositoy.save(calendar);
        }
    }

    public ResponseEntity findCalendarMonth(UserDetailsImpl userDetails){

        // 지금은 현재 월만 조회 가능, 과거 월 조회 x

        List<CalendarResponseDto> calendarDtoList = new ArrayList<>();
        List<Calendar> calendarList = calendarRepositoy.findAllByUserAndDateIsGreaterThanEqualAndDateIsLessThanEqual
                (userDetails.getUser(), LocalDate.now().withDayOfMonth(1), LocalDate.now());


        for(Calendar calendar : calendarList){
            CalendarResponseDto calendarResponseDto = CalendarResponseDto.builder()
                    .dayCalorie(calendar.getDayCalorie())
                    .dayWeight(calendar.getDayWeight())
                    .hasExercise(calendar.isHasExercise())
                    .hasMeal(calendar.isHasMeal())
                    .date(calendar.getDate())
                    .build();

            calendarDtoList.add(calendarResponseDto);

            // calendar가 비어있으면 빈 calendar를 생성해서 줄지 말지 결정해야 한다.
        }


        return ResponseEntity.ok().body(calendarDtoList);
    }
}
