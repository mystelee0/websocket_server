package com.example.websocket_server.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RoomUserRepositoryImpl implements RoomUserRepository{
    // 채팅방에 들어가있는 유저정보 저장
    Map<String, Set<String>> rooms = new HashMap<>();

    @Override
    public List<String> getUsersByRoomId(String roomId) {
        return new ArrayList<>(rooms.getOrDefault(roomId, new HashSet<>()));
    }

    @Override
    public void addUser(String roomId, String mobNum) {
        rooms.get(roomId).add(mobNum);
    }

    @Override
    public void deleteUser(String roomId, String mobNum) {

    }

    @Override
    public void createRoom(String roomId) {
        rooms.put(roomId,new HashSet<>());
    }

    @Override
    public void deleteRooom(String roomId) {

    }

    @PostConstruct
    void init(){
        System.out.println("@@@ 채팅방 생성 @@@");
        // 101 방 생성
        createRoom("101");
        // 채팅방에 유저 등록
        addUser("101","112");
        addUser("101","114");
    }
}
