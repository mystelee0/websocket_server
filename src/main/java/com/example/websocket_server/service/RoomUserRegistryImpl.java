package com.example.websocket_server.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomUserRegistryImpl implements RoomUserRegistry {

    @Override
    public List<String> getUsersByRoomId(String roomId) {
        //리스트 뽑아야함
        return List.of();
    }
}
