package com.example.websocket_server.repository;

import com.example.websocket_server.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository{

    Map<String,UserDTO> repo = new HashMap<>();

    @Override
    public UserDTO saveUser(UserDTO newUser) {
        repo.put(newUser.getMobNum(),newUser);
        return repo.get(newUser.getMobNum());
    }

    @Override
    public UserDTO findUser(UserDTO user) {

        return repo.get(user.getMobNum());
    }
}
