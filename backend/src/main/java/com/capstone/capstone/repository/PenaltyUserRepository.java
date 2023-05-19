package com.capstone.capstone.repository;

import com.capstone.capstone.model.PenaltyUser;
import com.capstone.capstone.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenaltyUserRepository extends JpaRepository<PenaltyUser, Long> {

}
