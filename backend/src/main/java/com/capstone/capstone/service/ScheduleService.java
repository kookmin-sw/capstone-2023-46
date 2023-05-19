package com.capstone.capstone.service;

import com.capstone.capstone.dto.ScheduleRequestDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.Schedule;
import com.capstone.capstone.repository.ScheduleRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public void saveSchedule(UserDetailsImpl userDetails, ScheduleRequestDto scheduleRequestDto){
        try {
            Schedule schedule = scheduleRepository.findByUser(userDetails.getUser()).get();
            schedule.getSchedule_id();
            throw new CustomException(ErrorCode.ALREADY_HAS_SCHEDULE);
        } catch (NoSuchElementException e) {
            Schedule schedule;
            LocalDate date = LocalDate.now();
            int day = date.getDayOfWeek().getValue() - 1;

            // 오늘 schedule 등록했는데, 가야하는 경우
            if(scheduleRequestDto.getScheduleList().get(day) == true) {
                schedule = Schedule.builder()
                        .checkSchedule(scheduleRequestDto.getScheduleList())
                        .isCheckedIn(false)
                        .user(userDetails.getUser())
                        .build();
            }
            // schedule 등록한 날에 운동 안 가는 경우
            else {
                schedule = Schedule.builder()
                        .checkSchedule(scheduleRequestDto.getScheduleList())
                        .isCheckedIn(true)
                        .user(userDetails.getUser())
                        .build();
            }
            scheduleRepository.save(schedule);
        }


    }

    public ResponseEntity editSchedule(UserDetailsImpl userDetails, ScheduleRequestDto scheduleRequestDto){
        Schedule schedule = scheduleRepository.findByUser(userDetails.getUser()).get();
        schedule.setCheckSchedule(scheduleRequestDto.getScheduleList());
        scheduleRepository.save(schedule);
        
        return ResponseEntity.ok().body("수정완료");
    }
    
    @Transactional
    public void setIsCheckIn(Schedule schedule, boolean visitation){
        schedule.setIsCheckedIn(visitation);
        scheduleRepository.save(schedule);
    }
    
    @Transactional
    public void clearCheckList(Schedule schedule){
        schedule.clearSchedule();
        scheduleRepository.save(schedule);
    }
}
