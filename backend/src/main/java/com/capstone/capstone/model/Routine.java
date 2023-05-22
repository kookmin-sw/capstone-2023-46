package com.capstone.capstone.model;

import com.capstone.capstone.dto.R_ExerciseRequestDto;
import com.capstone.capstone.dto.RoutineRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.reducing;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId;

    @Column
    private String routine_name;

    @Column Long total_set;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "routine", cascade = CascadeType.REMOVE)
    private List<Routine_Exercise> r_exercises;

    public void editRoutine(RoutineRequestDto routineRequestDto, List<R_ExerciseRequestDto> rExerciseRequestDtos){
        this.routine_name = routineRequestDto.getRoutine_name();
        this.total_set = rExerciseRequestDtos.stream()
                .map(R_ExerciseRequestDto::getSet)
                .collect(reducing(Long::sum)).get();
        this.r_exercises.clear();
        for(R_ExerciseRequestDto rExerciseRequestDto : rExerciseRequestDtos) {
            Routine_Exercise routineExercise = Routine_Exercise.builder()
                    .name(rExerciseRequestDto.getExercise_name())
                    .set(rExerciseRequestDto.getSet())
                    .build();
            r_exercises.add(routineExercise);
        }
    }
}
