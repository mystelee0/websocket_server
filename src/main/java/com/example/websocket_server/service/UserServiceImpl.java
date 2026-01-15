package com.example.websocket_server.service;

import com.example.websocket_server.CustomUserDetails;
import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.entity.User;
import com.example.websocket_server.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        System.out.println("인코딩 결과 "+encodedPassword);

        // 유저객체에 정보 대입
        User user = new User();
        user.setPassword(encodedPassword);
        user.setId(newUser.getId());
        user.setName(newUser.getName());
        user.setMessage("");
        user.setProfile("/114_profile.jpg");
        // 저장
        User savedUser = repo.saveUser(user);

        // 결과확인
        return savedUser != null;
    }

    @Override
    public User fetchUserInfo(String id) {
        Optional<User> OptionalUser = repo.findUser(id);

        if(OptionalUser.isEmpty()){
            return null;
        }else {
            User result = OptionalUser.get();
            return result;
        }
    }

    @PostConstruct
    public void init(){
        System.out.println("@@@@@ 유저정보 초기화 @@@@@");

        signUp(new UserAuthDTO("112","경찰","112"));
        signUp(new UserAuthDTO("114","상담원","114"));
    }
}
