package com.capstone.capstone.model;

import com.capstone.capstone.dto.MealRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meal_id;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private Long protein;

    @Column(nullable = false)
    private Long fat;

    @Column(nullable = false)
    private Long calorie;

    @Column(nullable = false)
    private Long carbohydrate;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    public Meal(MealRequestDto mealRequestDto, UserDetailsImpl userDetails){
        this.userNickname = userDetails.getUser().getNickname();
        this.protein = mealRequestDto.getProtein();
        this.fat = mealRequestDto.getFat();
        this.calorie = mealRequestDto.getCalorie();
        this.carbohydrate = mealRequestDto.getCarbohydrate();
    }

    public void setMeal(MealRequestDto mealRequestDto, UserDetailsImpl userDetails){
        this.protein = mealRequestDto.getProtein();
        this.fat = mealRequestDto.getFat();
        this.calorie = mealRequestDto.getCalorie();
        this.carbohydrate = mealRequestDto.getCarbohydrate();
    }

    public void setMeal(MealRequestDto mealRequestDto) {
    }
}
