package com.example.websocket_server.service;

import com.example.websocket_server.repository.RoomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomUserRegistryImpl implements RoomUserRegistry {

    @Autowired
    RoomUserRepository roomUserRepository;

    @Override
    public List<String> getUsersByRoomId(String roomId) {
        return roomUserRepository.getUsersByRoomId(roomId);
    }
}
