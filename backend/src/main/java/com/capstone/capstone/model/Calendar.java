package com.capstone.capstone.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendarId;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private boolean hasMeal;

    @Column
    private boolean hasExercise;

    @Column
    private Long dayCalorie;

    @Column Long dayWeight;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    public void setDayCalorie(Long calorie){
        this.dayCalorie += calorie;
    }

    public void setDayWeight(Long weight){
        this.dayWeight += weight;
    }
}
