package com.example.websocket_server.service;

import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    @Override
    public UserDTO fetchUserInfo(String mobNum) {
        Optional<UserDTO> user = repo.findUser(mobNum);

        if(user.isEmpty()){
            return null;
        }else {
            UserDTO result = user.get();
            result.setPassword("");
            return result;
        }
    }

    @PostConstruct
    public void init(){
        System.out.println("@@@@@ 유저정보 초기화 @@@@@");
        signUp(new UserAuthDTO(new UserDTO("112","경찰","112")));
        signUp(new UserAuthDTO(new UserDTO("114","상담원","114")));
    }
}
