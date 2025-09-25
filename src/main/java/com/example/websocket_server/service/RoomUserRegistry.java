package com.example.websocket_server.service;

import java.util.List;

public interface RoomUserRegistry {

    List<String> getUsersByRoomId(String roomId);
    
}
