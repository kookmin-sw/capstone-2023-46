package com.capstone.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "exercise_id")
    private List<Exercise> exercises;

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }
}
