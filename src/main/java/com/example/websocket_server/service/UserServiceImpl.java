package com.example.websocket_server.service;

import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    UserRepository repo;

    UserServiceImpl(UserRepository userRepository){
        this.repo=userRepository;
    }
    @Override
    public boolean signUp(UserDTO newUser) {

        UserDTO savedUser = repo.saveUser(newUser);

        return Objects.equals(newUser,savedUser);
    }

    @Override
    public String signIn(UserDTO user) {
        return "success";
    }
}
