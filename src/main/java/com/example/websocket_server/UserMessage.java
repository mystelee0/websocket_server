package com.example.websocket_server;

import com.example.websocket_server.dto.UserDTO;

public class UserMessage {

    private int messageType; // 1=일반메시지 /topic(broadcast), 2=방초대 메시지
    private String roomId;
    private UserDTO sender;
    private Object message;
    private String date;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
