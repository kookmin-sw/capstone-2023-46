package com.capstone.capstone.repository;

import com.capstone.capstone.model.Schedule;
import com.capstone.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByUser(User user);
}
