package com.capstone.capstone.service;

import com.capstone.capstone.dto.MessageDto;
import com.capstone.capstone.dto.ResponseDto.SMSResponseDto;
import com.capstone.capstone.model.PenaltyUser;
import com.capstone.capstone.model.User;
import com.capstone.capstone.repository.PenaltyUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final UserService userService;
    private final PenaltyUserRepository penaltyUserRepository;

    private final ScheduleService scheduleService;

    private final SMSService smsService;
    // 방문 확인
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void checkVisitation() {
        List<User> userList = userService.getAllUserWithOutNullSchedule();
        LocalDate date = LocalDate.now();
        int day = date.getDayOfWeek().getValue() - 2; // day는 1~7, 어제 날짜를 검색하니 -2
        for(User user : userList){
            // 어제 가기로 한 날짜면 true
            // 월요일이면 일요일 거 계산 따로 해줘야 한다.
            boolean isCheckedSchedule;
            if (day < 0)
                isCheckedSchedule = user.getSchedule().getCheckSchedule().get(6);
            else
                isCheckedSchedule = user.getSchedule().getCheckSchedule().get(day);

            // 가기로 한 날짜인데, checkIn 안 됐으면 penaltyUser로 등록, 추후 penalty 시행
            if (isCheckedSchedule == true && isCheckedSchedule != user.getSchedule().getIsCheckedIn()){
                PenaltyUser penaltyUser = PenaltyUser.builder()
                        .userName(user.getUsername())
                        .penaltyYellowPages(user.getPenaltyYellowPages())
                        .phoneNumber(user.getPhoneNumber())
                        .build();

                penaltyUserRepository.save(penaltyUser);
            }
            // isCheckIn false로 초기화, 어차피 당일 가는 걸로 위에서 penaltyUser 만드므로 false로 전부 만들어도 된다.
            scheduleService.setIsCheckIn(user.getSchedule(), false);

            // 만약 다음 주로 바뀌어 월요일이 된다면 checkList 초기화
            if (day + 1 == 0) {
                scheduleService.clearCheckList(user.getSchedule());
            }
        }

    }

    //penalty
    @Scheduled(cron = "0 0 12 * * *")
    public void imposePenalty()throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        List<PenaltyUser> penaltyUsers = penaltyUserRepository.findAll();
        Random rand = new Random();

        for(PenaltyUser penaltyUser : penaltyUsers){
            String randomPhoneNumber = penaltyUser.getPenaltyYellowPages().get(rand.nextInt(penaltyUser.getPenaltyYellowPages().size()));
            String penalty_pattern1 = String.format("안녕하십니까. %s (%s) 가자운동 사용자가 어제 운동을 하지 못하였습니다. " +
                            "%s 사용자가 운동을 열심히 갈 수 있게 응원의 메시지를 보내주세요!",
                    penaltyUser.getUserName(), penaltyUser.getPhoneNumber(), penaltyUser.getUserName());
            MessageDto messageDto = MessageDto.builder()
                    // 아래 번호에 randomPhoneNumber 넣기
                    .to(randomPhoneNumber)
                    // content 좀 더 다채롭게
                    .content(penalty_pattern1)
                    .build();

            SMSResponseDto smsResponseDto = smsService.sendSms(messageDto);
            System.out.println(smsResponseDto);
        }
    }

    // 월마다 모든 user의 chance 3으로 초기화
    @Transactional
    @Scheduled(cron = "0 0 0 1 * *")
    public void resetChance() {
        userService.resetChanceAllUser();
    }
}
