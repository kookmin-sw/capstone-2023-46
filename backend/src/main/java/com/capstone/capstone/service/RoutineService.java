//package com.capstone.capstone.service;
//
//import com.capstone.capstone.dto.RoutineRequestDto;
//import com.capstone.capstone.model.Routine;
//import com.capstone.capstone.repository.RoutineRepository;
//import com.capstone.capstone.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class RoutineService {
//    private final RoutineRepository routineRepository;
//
//    public void saveRoutine(UserDetailsImpl userDetails, RoutineRequestDto routineRequestDto){
//        Routine routine = Routine.builder()
//                .routine_name(routineRequestDto.getName())
//                .user(userDetails.getUser())
//                .build();
//
//        routineRepository.save(routine);
//    }
//
//}
