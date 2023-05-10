package com.capstone.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routine_id;

    @Column
    private String routine_name;

//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "user_id")
//    private User user;

//    @OneToMany(mappedBy = "routine", cascade = CascadeType.REMOVE)
//    private List<Routine_Exercise> r_exercises;

}
