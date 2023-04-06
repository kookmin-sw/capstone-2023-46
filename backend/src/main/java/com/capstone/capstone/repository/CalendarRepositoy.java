package com.capstone.capstone.repository;

import com.capstone.capstone.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CalendarRepositoy extends JpaRepository<Calendar, Long> {
    Optional<Calendar> findCalendarByUserNicknameAndDate(String userNickname, LocalDate localDate);
}