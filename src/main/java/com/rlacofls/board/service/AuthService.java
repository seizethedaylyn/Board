package com.rlacofls.board.service;

import com.rlacofls.board.dto.ResponseDto;
import com.rlacofls.board.dto.SignInDto;
import com.rlacofls.board.dto.SignInResponseDto;
import com.rlacofls.board.dto.SignUpDto;
import com.rlacofls.board.entity.UserEntity;
import com.rlacofls.board.repository.UserRepository;
import com.rlacofls.board.security.TokenProvider;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenProvider tokenProvider;

    public ResponseDto<?> signUp(SignUpDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        try{
            //email 중복 확인
            if(userRepository.existsById(userEmail))
                return ResponseDto.setFailed("Existed Email!!");

        }catch(Exception error){
            return ResponseDto.setFailed("DB error");
        }

        //비밀번호랑 비밀번호체크랑 맞지 않는 경우
        if(!userPassword.equals(userPasswordCheck))
            return ResponseDto.setFailed("Password doess not matched!");

        //userEntity 생성
        UserEntity userEntity = new UserEntity(dto);

        try{
            //userRepo 이용하여 디비에 엔티티 저장
            userRepository.save(userEntity);

        }catch(Exception error){
            return ResponseDto.setFailed("DB error!");
        }

        // 성공시 success Response 반환
        return ResponseDto.setSuccess("SignUp Success!!", null);
    }


    public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        try{
            boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
            if(!existed) return ResponseDto.setFailed("Sign In Information does not Matched!!");
        }catch(Exception Error){
            return ResponseDto.setFailed("Data Base Error");
        }

        UserEntity userEntity = null;
        try{
            userEntity = userRepository.findById(userEmail).get();
        }catch(Exception Error){
            return ResponseDto.setFailed("Data Base Error!");
        }

        userEntity.setUserPassword("");

        String token = tokenProvider.create(userEmail);
        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);

        return ResponseDto.setSuccess("Sign In Success", signInResponseDto);

    }
}
