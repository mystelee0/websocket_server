package com.example.websocket_server.repository;

import java.util.List;

public interface RoomUserRepository {

    // 채팅방 유저 조회
    List getUsersByRoomId(String roomId);

    // 채팅방 유저 추가
    void addUser(String roomId, String mobNum);

    // 채팅방 유저 삭제
    void deleteUser(String roomId, String mobNum);

    // 채팅방 생성
    void createRoom(String roomId);

    // 채팅방 삭제
    void deleteRooom(String roomId);
}
