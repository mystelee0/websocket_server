package com.example.websocket_server;

import com.example.websocket_server.service.RoomUserRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpSubscription;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class GreetingController {
    private SimpMessagingTemplate template;
    private RoomUserRegistry roomUserRegistry;

    public GreetingController(SimpMessagingTemplate template, RoomUserRegistry roomUserRegistry) {

        this.template = template;
        this.roomUserRegistry = roomUserRegistry;
    }

    @MessageMapping("/greetings")
    public void greet(String jsonMessage, Principal principal) {
        System.out.println("JSON Message "+jsonMessage);

        // JSON -> Object (UserMessage)  변환
        ObjectMapper objectMapper = new ObjectMapper();
        UserMessage userMessage;
        try {
            userMessage = objectMapper.readValue(jsonMessage,UserMessage.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException("JSON 파싱 실패", e);  // 예외 던지고 종료
        }

        //메시지 타입별 처리
        if(userMessage != null){
            switch(userMessage.getMessageType()){
                case 1:
                    // 일반적인 채팅 메시지
                    System.out.println("case 1");
                    //브로드캐스트 메시지 /topic/{roomId}
                    //리액트 useWebsocket.js:42 topic/101 구독하였음.
                    //this.template.convertAndSend("/topic/101", jsonMessage);
                    List<String> userList = roomUserRegistry.getUsersByRoomId("101");
                    System.out.println(userList.size());
                    for(String mobNum : userList){
                        this.template.convertAndSendToUser(mobNum,"/queue/message", userMessage);
                    }
                    break;
                case 2:
                    // 유저 초대,방나감 등의 메시지
                    System.out.println("case 2");
                    System.out.println(principal.getName());
                    //시스템에 보낸 메시지 /user/{userId}/queue/message
                    //this.template.convertAndSendToUser(principal.getName(),"/queue/message", userMessage);

                    //브로드캐스트 안하고 개인한테만 감
                    //리액트 useWebsocket.js:41 유저명으로 구독하였음.
                    this.template.convertAndSendToUser("114","/queue/message", userMessage);
                    break;
            }
        }

    }

    @Autowired
    private SimpUserRegistry simpUserRegistry;
    public void printUserSubscriptions() {
        // 1. 유저 정보 가져오기
//        SimpUser user = simpUserRegistry.getUser(principal.getName());
//        if (user == null) {
//            System.out.println("유저 없음: " + principal.getName());
//            return;
//        }
//
//        // 2. 세션 ID 찾기 (첫번째 세션 사용)
//        String sessionId = null;
//        for (SimpSession session : user.getSessions()) {
//            sessionId = session.getId();
//            break;  // 첫 세션 ID만 사용
//        }
//
//        // 3. 헤더 생성
//        MessageHeaders headers = createHeaders(sessionId);
        for (SimpUser user : simpUserRegistry.getUsers()) {
            System.out.println("User: " + user.getName());

            for (SimpSession session : user.getSessions()) {
                System.out.println("  Session ID: " + session.getId());

                for (SimpSubscription subscription : session.getSubscriptions()) {
                    System.out.println("    Subscribed to: " + subscription.getDestination());
                }
            }
        }
    }

    private MessageHeaders createHeaders(@Nullable String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        if (sessionId != null) headerAccessor.setSessionId(sessionId);  // ❌ 주의!
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
