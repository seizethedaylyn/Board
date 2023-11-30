package com.rlacofls.board.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    //jwt 생성 및 검증을 위한 키
    private static final Key SECURITY_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); //512로 security key 만들어줌

    //jwt 생성하는 method
    public String create (String userEmail){
        //만료 날짜를 현재 날짜 + 1시간으로 설정
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS ));

        //jwt 생성
        return Jwts.builder()
                //암호화에 사용될 알고리즘, 키
                .signWith(SignatureAlgorithm.HS512, TokenProvider.SECURITY_KEY)
                // jwt 제목, 생성일, 만료일
                .setSubject(userEmail).setIssuedAt(new Date()).setExpiration(exprTime)
                // 생성!
                .compact();
    }

    //jwt 검증
    public String validate(String token){
        //매개변수로 받은 token을 키를 사용해서 복호화(디코딩)
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        //복호환된 토큰의 payload에서 제목을 가져옴
        return claims.getSubject();
    }
}
