package com.example.websocket_server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    String signUpRequest(@RequestBody String mobNum){
        System.out.println("mobNum "+mobNum);
        return "success!!!";
    }

}
