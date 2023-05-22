package com.capstone.capstone.model;

import com.capstone.capstone.dto.ExerciseRequestDto;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long set;
//lew05
    @Column(nullable = false)
    @ElementCollection(targetClass = Long.class)
    private List<Long> weights;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    public Exercise(ExerciseRequestDto exerciseRequestDto, UserDetailsImpl userDetails){
        weights = new ArrayList<>();
        this.name = exerciseRequestDto.getName();
        this.set = exerciseRequestDto.getSet();
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        for(int i = 0; i < this.set; i++){
            this.weights.add(exerciseRequestDto.getWeights().get(i));
        }
    }

    public void setExercise(ExerciseRequestDto exerciseRequestDto){
        calendar.setDayWeight(-this.getExerciseWeight());

        this.name = exerciseRequestDto.getName();
        this.set = exerciseRequestDto.getSet();
        this.weights = exerciseRequestDto.getWeights();
        // 시간도 초기화를 해야할까?
        calendar.setDayWeight(this.getExerciseWeight());
    }

    public long getExerciseWeight(){
        int dayWeight = 0;
        for(int i = 0; i < this.set; i++){
            dayWeight += this.weights.get(i);
        }
        return dayWeight;
    }
}
