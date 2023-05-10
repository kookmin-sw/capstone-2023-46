package com.capstone.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long picture_id;

    @Column
    private String UserNickname;

    @Column
    private LocalTime time;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "Calendar_id")
    private Calendar calendar;
}
