package com.example.websocket_server;

import com.example.websocket_server.dto.UserAuthDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    UserAuthDTO user;

    public CustomUserDetails(UserAuthDTO user){
        this.user=user;
    }

    public UserAuthDTO getUser(){
        return user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // 권한 없으면 빈 리스트
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // 또는 getPwd()
    }

    @Override
    public String getUsername() {
        return user.getMobNum(); // 사용자 식별자 (아이디 역할)
    }

    // 아래는 보통 true로 처리
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
