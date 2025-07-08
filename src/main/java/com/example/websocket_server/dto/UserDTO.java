package com.example.websocket_server.dto;

import java.util.Objects;

public class UserDTO{
    String mobNum;
    String nickName;
    String password;

    public UserDTO(String mobNum, String nickName, String password) {
        this.mobNum = mobNum;
        this.nickName = nickName;
        this.password = password;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("핸드폰번호 : %s, 닉네임 : %s, 비밀번호 : %s",this.mobNum,this.nickName,this.password);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(mobNum, userDTO.mobNum) && Objects.equals(nickName, userDTO.nickName) && Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobNum, nickName, password);
    }
}
