package com.example.websocket_server;

import com.example.websocket_server.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String id;
    private String password;

    public CustomUserDetails(User user){
        this.id = user.getId();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // 권한 없으면 빈 리스트
    }

    @Override
    public String getPassword() {
        return this.password; // 또는 getPwd()
    }

    @Override
    public String getUsername() {
        return this.id; // 사용자 식별자 (아이디 역할)
    }

    // 아래는 보통 true로 처리
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
