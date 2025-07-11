package com.example.websocket_server.service;

import com.example.websocket_server.CustomUserDetails;
import com.example.websocket_server.dto.UserAuthDTO;
import com.example.websocket_server.dto.UserDTO;
import com.example.websocket_server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mobNum) throws UsernameNotFoundException {
        UserDTO user = userRepository.findUser(mobNum)
                .orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
        System.out.println("가져온 내역 : "+user);

        return new CustomUserDetails(new UserAuthDTO(user));
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getMobNum())
//                .password(user.getPassword())  // 암호화된 비밀번호
//                .authorities(Collections.emptyList())  // 권한 없으면 빈 리스트
//                .build();
    }
}
