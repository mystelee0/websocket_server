package com.example.websocket_server.controller;

import com.example.websocket_server.CustomUserDetails;
import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.entity.User;
import com.example.websocket_server.service.UserService;
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
    UserService userService;

    AuthController(AuthenticationManager authenticationManager, UserService userService){
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    ResponseEntity loginRequest(@RequestBody UserAuthDTO user, HttpSession session){
        System.out.println("로그인 요청 "+user.getId()+user.getPassword());

        try{
            //인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    user.getId(),
                    user.getPassword()
            );
            //인증 수행 - CustomUserDetailService.loadUserByUsername() 호출
            Authentication authentication = authenticationManager.authenticate(authToken);

            //인증 성공 시 SecurityContext안에 인증정보 저장 (자동저장해준다함)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // SecurityContext를 세션에 저장
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());

            CustomUserDetails loginUser = (CustomUserDetails) authentication.getPrincipal();

            // id로 조회 반환
            User foundUser = userService.fetchUserInfo(loginUser.getUsername());
            UserDTO forResponse = new UserDTO(foundUser);
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

        User foundUser = userService.fetchUserInfo(user.getUsername());
        UserDTO forResponse = new UserDTO(foundUser);

        return ResponseEntity.ok(forResponse);
    }

}
