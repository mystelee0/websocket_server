package com.example.websocket_server.controller;

import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
