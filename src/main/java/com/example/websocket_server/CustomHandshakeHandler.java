package com.example.websocket_server;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // attributes에서 userId를 꺼내 Principal 생성
        String userId = (String) attributes.get("userId");
        System.out.println("커스텀핸들러 실행 "+userId);
        return new StompPrincipal(userId); // userId가 Principal.getName()이 됨
    }
}
