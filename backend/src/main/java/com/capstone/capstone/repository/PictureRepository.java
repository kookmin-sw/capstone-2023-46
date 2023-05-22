package com.capstone.capstone.repository;

import com.capstone.capstone.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {


    List<Picture> findAllByCalendar_CalendarIdAndDate(Long calnedar_id, LocalDate date);
}
