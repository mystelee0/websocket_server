package com.example.websocket_server.repository;

import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.entity.User;
import java.util.Optional;

public interface UserRepository {
    //회원가입용
    User saveUser(User newUser);

    //로그인용
    Optional<User> findUser(String id);
}
