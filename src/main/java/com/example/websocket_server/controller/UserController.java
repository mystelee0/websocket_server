package com.example.websocket_server.controller;

import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    String signUpRequest(@RequestBody UserAuthDTO newUser){
        System.out.println("회원가입 요청 "+newUser);

        boolean result = userService.signUp(newUser);

        return result?"success":"fail";
    }

    @GetMapping("/users/{mobNum}")
    ResponseEntity getUserInfo(@PathVariable String mobNum){

        UserDTO user = userService.fetchUserInfo(mobNum);

        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터 없음");
        }
        return ResponseEntity.ok(user);
    }
}
