package com.example.websocket_server.repository;

import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;

import java.util.Optional;

public interface UserRepository {
    //회원가입용
    UserDTO saveUser(UserAuthDTO newUser);

    //로그인용
    Optional<UserDTO> findUser(String mobNum);
}
