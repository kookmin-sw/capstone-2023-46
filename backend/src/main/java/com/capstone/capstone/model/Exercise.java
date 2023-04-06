package com.capstone.capstone.model;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long set;

    @Column(nullable = false)
    @ElementCollection(targetClass = Long.class)
    private List<Long> weights;


    public Exercise(ExerciseRequestDto exerciseRequestDto, UserDetailsImpl userDetails){
        weights = new ArrayList<>();
        this.userNickname = userDetails.getUser().getNickname();
        this.name = exerciseRequestDto.getName();
        this.set = exerciseRequestDto.getSet();
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        for(int i = 0; i < this.set; i++){
            this.weights.add(exerciseRequestDto.getWeights().get(i));
        }
    }

    public void setExercise(ExerciseRequestDto exerciseRequestDto){
        weights = new ArrayList<>();
        this.name = exerciseRequestDto.getName();
        this.set = exerciseRequestDto.getSet();
        // 시간도 초기화를 해야할까?
        for(int i = 0; i < this.set; i++){
            this.weights.add(exerciseRequestDto.getWeights().get(i));
        }
    }
}
