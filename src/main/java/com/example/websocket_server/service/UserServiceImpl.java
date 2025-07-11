package com.example.websocket_server.service;

import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    UserRepository repo;
    PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.repo=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public boolean signUp(UserAuthDTO newUser) {
        System.out.println("회원가입 서비스 시작");
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        System.out.println("인코딩 결과 "+encodedPassword);
        newUser.setPassword(encodedPassword);

        UserDTO savedUser = repo.saveUser(newUser);

        return Objects.equals(newUser,savedUser);
    }


}
