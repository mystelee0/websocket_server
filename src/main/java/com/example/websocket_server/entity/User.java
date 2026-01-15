package com.example.websocket_server.entity;

public class User {
    String id;
    String name;
    String password;
    String profile;
    String message;

    public User() {
    }

    public User(String id, String name, String password, String profile, String message) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.profile = profile;
        this.message = message;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
