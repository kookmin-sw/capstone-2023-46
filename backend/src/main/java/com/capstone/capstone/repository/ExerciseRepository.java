package com.capstone.capstone.repository;

import com.capstone.capstone.model.Exercise;
import com.capstone.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findExerciseByUserNickname(String userNickname);

    Optional<Exercise> findById(Long id);

    List<Exercise> findAllByDateAndUserNickname(LocalDate localDate, String userNickname);
}
