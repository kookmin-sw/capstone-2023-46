package com.capstone.capstone.service;



import com.capstone.capstone.dto.AddressRequestDto;
import com.capstone.capstone.dto.ResponseDto.AddressResponseDto;
import com.capstone.capstone.dto.SignupRequestDto;
//import com.capstone.capstone.dto.responseDto.SocialLoginResponseDto;
import com.capstone.capstone.dto.PhoneNumbersRequestDto;
import com.capstone.capstone.exceptionHandler.CustomException;
import com.capstone.capstone.exceptionHandler.ErrorCode;
import com.capstone.capstone.model.User;

import com.capstone.capstone.repository.UserRepository;
import com.capstone.capstone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    @Value("${spring.admin.token}")
//    String ADMIN_TOKEN;

    public ResponseEntity signupUser(SignupRequestDto requestDto) {

        String passwordPattern = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}"; //영어, 숫자 8자이상 20이하
//영문, 숫자, 특수기호 4자이상 20이하 "(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{4,20}"
// "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}"; //영어, 숫자 8자이상 20이하
//        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; //이메일 정규식 패턴
        String emailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"; //이메일 정규식 패턴
        String nicknamePattern = "^[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣~!@#$%^&*]{2,8}"; //닉네임 정규식 패턴
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();

        //username 정규식 맞지 않는 경우 오류메시지 전달
        if (username.equals(""))
            throw new CustomException(ErrorCode.EMPTY_USERNAME);
        else if (!Pattern.matches(emailPattern, username))
            throw new CustomException(ErrorCode.USERNAME_WRONG);
        else if (userRepository.findByUsername(username).isPresent())
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);

        //nickname 정규식 맞지 않는 경우 오류메시지 전달
        if (nickname.equals(""))
            throw new CustomException(ErrorCode.EMPTY_NICKNAME);
        else if (userRepository.findByNickname(nickname).isPresent())
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        else if (2 > nickname.length() || 8 < nickname.length())
            throw new CustomException(ErrorCode.NICKNAME_LEGNTH);
        else if (!Pattern.matches(nicknamePattern, nickname))
            throw new CustomException(ErrorCode.NICKNAME_WRONG);

        //password 정규식 맞지 않는 경우 오류메시지 전달
        if (password.equals(""))
            throw new CustomException(ErrorCode.EMPTY_PASSWORD);
        else if (8 > password.length() || 20 < password.length())
            throw new CustomException(ErrorCode.PASSWORD_LEGNTH);
        else if (!Pattern.matches(passwordPattern, password))
            throw new CustomException(ErrorCode.PASSWORD_WRONG);

        password = passwordEncoder.encode(requestDto.getPassword()); // 패스워드 암호화
        User user = new User(username, nickname, password);
        userRepository.save(user);
        return new ResponseEntity("회원가입을 축하합니다", HttpStatus.OK);
    }

    //username 중복체크
    public ResponseEntity checkUsername(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; //이메일 정규식 패턴

        //username 정규식 맞지 않는 경우 오류메시지 전달
        if (username.equals(""))
            throw new CustomException(ErrorCode.EMPTY_USERNAME);
        else if (!Pattern.matches(emailPattern, username))
            throw new CustomException(ErrorCode.USERNAME_WRONG);
        else if (userRepository.findByUsername(username).isPresent())
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);

        return new ResponseEntity("사용 가능한 이메일입니다.", HttpStatus.OK);
    }

    //nickname 중복체크
    public ResponseEntity checkNickname(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String nicknamePattern = "^[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣~!@#$%^&*]{2,8}"; //닉네임 정규식 패턴

        //nickname 정규식 맞지 않는 경우 오류메시지 전달
        if (nickname.equals(""))
            throw new CustomException(ErrorCode.EMPTY_NICKNAME);
        else if (userRepository.findByNickname(nickname).isPresent())
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        else if (2 > nickname.length() || 8 < nickname.length())
            throw new CustomException(ErrorCode.NICKNAME_LEGNTH);
        else if (!Pattern.matches(nicknamePattern, nickname))
            throw new CustomException(ErrorCode.NICKNAME_WRONG);

        return new ResponseEntity("사용 가능한 닉네임입니다.", HttpStatus.OK);
    }

    public ResponseEntity editAddress(UserDetailsImpl userDetails, AddressRequestDto addressRequestDto){
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        user.setGymAddress(addressRequestDto.getAddress());
        userRepository.save(user);

        return ResponseEntity.ok().body("주소 저장 완료");
    }

    public ResponseEntity getAddress(UserDetailsImpl userDetails){
        AddressResponseDto addressResponseDto = AddressResponseDto.builder()
                .address(userRepository.findByUsername(userDetails.getUsername()).get().getGymAddress())
                .build();
        return ResponseEntity.ok().body(addressResponseDto);
    }

    public ResponseEntity editPhoneNumber(UserDetailsImpl userDetails, PhoneNumbersRequestDto phoneNumbersRequestDto){
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        user.setPhoneNumber(phoneNumbersRequestDto.getPhoneNumber());
        user.setPenaltyYellowPages(phoneNumbersRequestDto.getYellowPage());
        return ResponseEntity.ok().body("핸드폰 번호 및 전화번호부 저장 완료");
    }

    public User getUser(UserDetailsImpl userDetails){
        return userRepository.findByUsername(userDetails.getUsername()).get();
    }

    @Transactional
    public List<User> getAllUserWithOutNullSchedule(){
        List<User> userList = userRepository.findAllByScheduleIsNotNull();

        return userList;
    }

    @Transactional
    public void resetChanceAllUser(){
        List<User> userList = userRepository.findAll();
        for(User user : userList){
            user.setChance(new Long(3));
            userRepository.save(user);
        }
    }

}