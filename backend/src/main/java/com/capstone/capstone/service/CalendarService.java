package com.capstone.capstone.service;

import com.capstone.capstone.model.Calendar;
import com.capstone.capstone.repository.CalendarRepositoy;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepositoy calendarRepositoy;

    @Transactional
    public void saveCalendar(UserDetailsImpl userDetails){
//        try {
//            Optional<Calendar> calendar = calendarRepositoy.findCalendarByUserAndDate(userDetails.getUser(), LocalDate.now());
//        }
//        catch (Exception e) {
            Calendar calendar = Calendar.builder()
                    .user(userDetails.getUser())
                    .date(LocalDate.now())
                    .userNickname(userDetails.getUser().getNickname())
                    .build();

            calendarRepositoy.save(calendar);

    }

    public String findCalendar(UserDetailsImpl userDetails){
        Optional<Calendar> calendar = calendarRepositoy.findCalendarByUserAndDate(userDetails.getUser(), LocalDate.now());
        return calendar.get().getExercises().get(0).getName();
    }

    public void deleteCalendar(UserDetailsImpl userDetails, Long id){

    }

    public void updateCalendar(UserDetailsImpl userDetails, Long id){

    }
}
