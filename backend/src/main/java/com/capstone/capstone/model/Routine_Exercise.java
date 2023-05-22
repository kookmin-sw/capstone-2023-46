package com.capstone.capstone.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Routine_Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long R_Exercise_Id;

    @Column
    private String name;

    @Column
    private Long set;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;
}
