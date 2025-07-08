package com.example.websocket_server.service;

import com.example.websocket_server.dto.UserDTO;

public interface UserService {
    //회원가입
    boolean signUp(UserDTO newUser);

    //로그인
    String signIn(UserDTO user);
}
