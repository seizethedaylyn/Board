//package com.rlacofls.board.controller;
//
//import com.rlacofls.board.dto.ResponseDto;
//import com.rlacofls.board.dto.SignInDto;
//import com.rlacofls.board.dto.SignInResponseDto;
//import com.rlacofls.board.dto.SignUpDto;
//
//import com.rlacofls.board.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired AuthService authService;
//
//    @PostMapping("/signUp")
//    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody){
//        ResponseDto<?> result = authService.signUp(requestBody);
//        return result;
//    }
//
//    @PostMapping("/signIn")
//    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody){
//        ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
//        return result;
//    }
//}
//
////controller는 서버와 클라이언트의 접점