package com.capstone.capstone.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    // 각 type을 boolean으로 처리할지, string을 통해 처리할 지
    @Column
    @ElementCollection(targetClass = boolean.class)
    private List<Boolean> checkSchedule;
    @Column
    private Boolean isCheckedIn;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    // 매주 일요일 24시에 schedule 초기화
    public void clearSchedule(){
        checkSchedule.clear();
    }



}
