package com.example.websocket_server.controller;

import com.example.websocket_server.CustomUserDetails;
import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.entity.User;
import com.example.websocket_server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    ResponseEntity<String> signUpRequest(@RequestBody UserAuthDTO newUser){
        System.out.println("회원가입 요청 "+newUser);

        boolean result = userService.signUp(newUser);

        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }

    @GetMapping("/users/{id}")
    ResponseEntity getUserInfo(@PathVariable String id){

        User user = userService.fetchUserInfo(id);

        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터 없음");
        }
        return ResponseEntity.ok(user);
    }
}
