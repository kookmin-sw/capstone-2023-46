package com.capstone.capstone.repository;

import com.capstone.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // username(로그인 id) 존재 여부
    Optional<User> findByUsername(String username);

    //닉네임 중복 검사 할 때 사용
    Optional<User> findByNickname(String nickname);

    Optional<User> findById(Long userId);

    List<User> findAllByScheduleIsNotNull();
}