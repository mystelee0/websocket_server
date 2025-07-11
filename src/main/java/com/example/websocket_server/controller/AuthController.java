package com.example.websocket_server.controller;

import com.example.websocket_server.CustomUserDetails;
import com.example.websocket_server.dto.UserAuthDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    AuthenticationManager authenticationManager;

    AuthController(AuthenticationManager authenticationManager){
        this. authenticationManager = authenticationManager;
    }

    @PostMapping("/auth/login")
    ResponseEntity loginRequest(@RequestBody UserAuthDTO user, HttpSession session){
        System.out.println("로그인 요청 "+user);

        try{
            //인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    user.getMobNum(),
                    user.getPassword()
            );
            //인증 수행 - CustomUserDetailService.loadUserByUsername() 호출
            Authentication authentication = authenticationManager.authenticate(authToken);

            //인증 성공 시 SecurityContext안에 인증정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // SecurityContext를 세션에 저장
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());

            CustomUserDetails loginUser = (CustomUserDetails) authentication.getPrincipal();

            //로그인 성공 시, 응답을 위한 객체 생성 (비밀번호 지우고 클라이언트에 전달)
            UserAuthDTO forResponse = new UserAuthDTO(loginUser.getUser());
            forResponse.setPassword(null);
            return ResponseEntity.ok(forResponse);

        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

    }

    @GetMapping("/auth/me")
    ResponseEntity getUserInfo(@AuthenticationPrincipal CustomUserDetails user){

        if(user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        UserAuthDTO forResponse = new UserAuthDTO(user.getUser());
        forResponse.setPassword(null);
        return ResponseEntity.ok(forResponse);
    }
}
