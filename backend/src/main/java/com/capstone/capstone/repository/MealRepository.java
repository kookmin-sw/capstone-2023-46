package com.capstone.capstone.repository;

import com.capstone.capstone.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByUserNickname(String userNickname);

    Optional<Meal> findById(Long id);

    List<Meal> findAllByDateAndUserNickname(LocalDate localDate, String userNickname);
}

