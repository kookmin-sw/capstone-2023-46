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
public class PenaltyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  penaltyUser_id;

    @Column
    private String userName;

    @Column(nullable = false)
    @ElementCollection(targetClass = String.class)
    private List<String> penaltyYellowPages;

}
