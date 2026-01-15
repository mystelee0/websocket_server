package com.example.websocket_server.repository;

import com.example.websocket_server.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository{

    static Map<String, User> repo = new HashMap<>();

    @Override
    public User saveUser(User newUser) {
        System.out.println("인메모리 레파지토리 시작");

        repo.put(newUser.getId(), newUser);
        return repo.get(newUser.getId());
    }

    @Override
    public Optional<User> findUser(String id) {

        return Optional.ofNullable(repo.get(id));
    }
}
