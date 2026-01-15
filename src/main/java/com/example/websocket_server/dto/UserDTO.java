package com.example.websocket_server.dto;

import com.example.websocket_server.CustomUserDetails;
import com.example.websocket_server.entity.User;

import java.util.Objects;

public class UserDTO {
    String id;
    String name;
    String profile;
    String message;

    public UserDTO(){}

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profile = user.getProfile();
        this.message = user.getMessage();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("핸드폰번호 : %s, 닉네임 : %s, 비밀번호 : %s",this.id,this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
