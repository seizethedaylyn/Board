package protect.pard.filter;

import com.rlacofls.board.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private TokenProvider tokenProvider;
    @Autowired
    public JwtAuthenticationFilter(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{
        String token = parseBearerToken(request);

        try {
            if(token != null && !token.equalsIgnoreCase("null")){
                String userEmail = tokenProvider.validate(token);
                AbstractAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        }catch (Exception e){
            e.printStackTrace(); //에러 찍어라
        }
        filterChain.doFilter(request, response);

    }

    private String parseBearerToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Autorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;
    } //여기가 중요하다~
}
//jwt가 들어오면, 맞는건지 아닌지 체크 아닌거 거르겠다~


//package com.rlacofls.board.filter;
//
//import com.rlacofls.board.security.TokenProvider;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private TokenProvider tokenProvider;
//    @Autowired
//    public JwtAuthenticationFilter(TokenProvider tokenProvider){
//        this.tokenProvider = tokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException{
//        String token = parseBearerToken(request);
//
//        try {
//            if(token != null && !token.equalsIgnoreCase("null")){
//                String userEmail = tokenProvider.validate(token);
//                AbstractAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
//                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//                securityContext.setAuthentication(authenticationToken);
//                SecurityContextHolder.setContext(securityContext);
//            }
//        }catch (Exception e){
//            e.printStackTrace(); //에러 찍어라
//        }
//        filterChain.doFilter(request, response);
//
//    }
//
//    private String parseBearerToken(HttpServletRequest request){
//        String bearerToken = request.getHeader("Autorization");
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
//            return bearerToken.substring(7);
//        return null;
//    } //여기가 중요하다~
//}
////jwt가 들어오면, 맞는건지 아닌지 체크 아닌거 거르겠다~