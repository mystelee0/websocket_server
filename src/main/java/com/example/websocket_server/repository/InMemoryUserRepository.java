package com.example.websocket_server.repository;

import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository{

    static Map<String, UserDTO> repo = new HashMap<>();

    @Override
    public UserDTO saveUser(UserAuthDTO newUser) {
        System.out.println("인메모리 레파지토리 시작");

        repo.put(newUser.getMobNum(),
                new UserDTO(newUser.getMobNum(),newUser.getNickName(),newUser.getPassword()));
        return repo.get(newUser.getMobNum());
    }

    @Override
    public Optional<UserDTO> findUser(String mobNum) {

        return Optional.ofNullable(repo.get(mobNum));
    }
}
