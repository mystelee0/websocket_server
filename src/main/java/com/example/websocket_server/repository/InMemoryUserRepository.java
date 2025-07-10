package com.example.websocket_server.repository;

import com.example.websocket_server.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository{

    Map<String,UserDTO> repo = new HashMap<>();

    @Override
    public UserDTO saveUser(UserDTO newUser) {
        System.out.println("인메모리 레파지토리 시작");
        repo.put(newUser.getMobNum(),newUser);
        return repo.get(newUser.getMobNum());
    }

    @Override
    public Optional<UserDTO> findUser(String mobNum) {

        return Optional.ofNullable(repo.get(mobNum));
    }
}
