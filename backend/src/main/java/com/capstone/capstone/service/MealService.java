package com.capstone.capstone.service;

import com.capstone.capstone.dto.MealRequestDto;
import com.capstone.capstone.dto.ResponseDto.MealResponseDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.Calendar;
import com.capstone.capstone.model.Meal;
import com.capstone.capstone.repository.CalendarRepositoy;
import com.capstone.capstone.repository.MealRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final CalendarRepositoy calendarRepositoy;

    @Transactional
    public void saveMeal(MealRequestDto mealRequestDto, UserDetailsImpl userDetails){
        Calendar calendar = calendarRepositoy.findCalendarByUserAndDate(userDetails.getUser(), LocalDate.now()).get();
        Meal meal = Meal.builder()
                .protein(mealRequestDto.getProtein())
                .fat(mealRequestDto.getFat())
                .calorie(mealRequestDto.getCalorie())
                .carbohydrate(mealRequestDto.getCarbohydrate())
                .calendar(calendar)
                .date(LocalDate.now())
                .build();

        mealRepository.save(meal);
    }

    @Transactional
    public void deleteMeal(UserDetailsImpl userDetails, Long id){
        Optional<Meal> meal = mealRepository.findById(id);
        if (!meal.get().getUserNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        mealRepository.delete(meal.get());
    }

    @Transactional
    public ResponseEntity editMeal(UserDetailsImpl userDetails, Long id, MealRequestDto mealRequestDto){
        Optional<Meal> meal = mealRepository.findById(id);
        if (!meal.get().getUserNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        meal.get().setMeal(mealRequestDto);
        mealRepository.save(meal.get());

        return ResponseEntity.ok().body(meal.get());
    }
    @Transactional
    public ResponseEntity getMeal(UserDetailsImpl userDetails, String date) {
        List<Meal> mealList = mealRepository.findAllByDateAndUserNickname(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), userDetails.getUser().getNickname());
        List<MealResponseDto> responseDtos = new ArrayList<>();
        if (!mealList.get(0).getUserNickname().equals(userDetails.getUser().getNickname()))
            throw new CustomException(ErrorCode.INVALID_EXERCISE_ID);

        for (Meal meal : mealList) {
            responseDtos.add(new MealResponseDto().builder()
                    .meal_id(meal.getMeal_id())
                    .protein(meal.getProtein())
                    .fat(meal.getFat())
                    .date(meal.getDate())
                    .calorie(meal.getCalorie())
                    .carbohydrate(meal.getCarbohydrate())
                    .build());
        }
        return ResponseEntity.ok().body(responseDtos);
    }

}
