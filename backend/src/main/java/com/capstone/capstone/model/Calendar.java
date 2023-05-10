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
    private Long calendar_id;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.REMOVE)
    private List<Exercise> exercises;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Picture> pictures;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;


    public void addExercise(Exercise exercise){
        System.out.println(1);
        exercises.add(exercise);
        System.out.println(exercise.getExercise_id());
    }
}
