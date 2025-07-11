package com.example.websocket_server.service;

import com.example.websocket_server.dto.UserAuthDTO;

public interface UserService {
    //회원가입
    boolean signUp(UserAuthDTO newUser);

    //로그인
    //String signIn(UserDTO user);
}
