package com.capstone.capstone.service;

import com.capstone.capstone.model.Calendar;
import com.capstone.capstone.repository.CalendarRepositoy;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepositoy calendarRepositoy;

    @Transactional
    public void saveCalendar(UserDetailsImpl userDetails){
        Calendar calendar = Calendar.builder()
                .date(LocalDate.now())
                .userNickname(userDetails.getUser().getNickname())
                .build();

        calendarRepositoy.save(calendar);

    }

    public Calendar findCalendar(UserDetailsImpl userDetails){
        Optional<Calendar> calendar = calendarRepositoy.findCalendarByUserNicknameAndDate(userDetails.getUser().getNickname(), LocalDate.now());
        return calendar.get();
    }
}
