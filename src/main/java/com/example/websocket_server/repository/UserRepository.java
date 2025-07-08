package com.example.websocket_server.repository;

import com.example.websocket_server.dto.UserDTO;

public interface UserRepository {
    //회원가입용
    UserDTO saveUser(UserDTO newUser);

    //로그인용
    UserDTO findUser(UserDTO user);
}
