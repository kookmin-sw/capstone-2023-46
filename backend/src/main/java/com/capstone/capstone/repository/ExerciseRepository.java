package com.capstone.capstone.repository;

import com.capstone.capstone.model.Exercise;
import com.capstone.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
