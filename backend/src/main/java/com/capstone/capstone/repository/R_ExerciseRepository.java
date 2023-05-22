package com.capstone.capstone.repository;

import com.capstone.capstone.model.Routine;
import com.capstone.capstone.model.Routine_Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface R_ExerciseRepository extends JpaRepository<Routine_Exercise, Long> {
    List<Routine_Exercise> findAllByRoutine_RoutineId(Long routine_id);
}
