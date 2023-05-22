package com.capstone.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long picture_id;

    @Column
    private String UserNickname;

    @Column
    private LocalDate date;

    @Column
    private String urls;

    @ManyToOne
    @JoinColumn(name = "Calendar_id")
    private Calendar calendar;
}
