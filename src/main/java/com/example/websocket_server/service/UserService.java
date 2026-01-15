package com.example.websocket_server.service;

import com.example.websocket_server.CustomUserDetails;
import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.entity.User;

public interface UserService {
    //회원가입
    boolean signUp(UserAuthDTO newUser);

    //유저조회
    User fetchUserInfo(String id);
}
