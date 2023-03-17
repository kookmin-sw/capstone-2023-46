package com.capstone.capstone.model;

import com.capstone.capstone.dto.ExerciseRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long set;

    @Column(nullable = false)
    @ElementCollection(targetClass = Long.class)
    private List<Long> weights;

    public Exercise(ExerciseRequestDto exerciseRequestDto){
        this.name = exerciseRequestDto.getName();
        this.set = exerciseRequestDto.getSet();
        weights = new ArrayList<>();
        for(int i = 0; i < this.set; i++){
            this.weights.add(exerciseRequestDto.getWeights().get(i));
        }
    }
}
