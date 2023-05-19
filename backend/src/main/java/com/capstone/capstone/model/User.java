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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; //email 형식인 username

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private String gymAddress;

    // penalty용 연락처
    @Column(nullable = false)
    @ElementCollection(targetClass = String.class)
    private List<String> penaltyYellowPages;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    Schedule schedule;

    public User(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }


}