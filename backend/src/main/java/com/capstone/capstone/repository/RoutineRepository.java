package com.capstone.capstone.repository;

import com.capstone.capstone.model.Routine;
import com.capstone.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findAllByUser(User user);
}
