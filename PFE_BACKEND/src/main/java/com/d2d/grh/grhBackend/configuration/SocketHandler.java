package com.d2d.grh.grhBackend.configuration;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    int messagecount = 0;

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String receivedMessage = message.getPayload();
        System.out.println("im come: "+receivedMessage);
        String res = "from server : "+receivedMessage;
        session.sendMessage(new TextMessage((res)));
    }

//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message)
//            throws InterruptedException, IOException {
//
//        // parse message
//        Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
//
//        // send message to all sessions
//        // for (WebSocketSession webSocketSession : sessions) {
//        // Map value = new Gson().fromJson(message.getPayload(), Map.class);
//        // webSocketSession.sendMessage(new TextMessage(message.getPayload()));
//        // }
//
//        if (value.get("name") != null){
//            System.out.println("value:  "+value.get("name"));
//            System.out.println("value:  "+value.get("message"));
//            System.out.println("value:  "+value.get("type"));
//            session.sendMessage(new TextMessage(message.getPayload()));
//
//        }
//
//
//        if (value.keySet().contains("subscribe")) {
//            System.out.println(message);
//            System.out.println(message.getPayload());
//            System.out.println(value.get("name"));
//            session.sendMessage(new TextMessage(message.getPayload()));
//
//            // start the service with the subscribe name
//        } else if (value.keySet().contains("unsubscribe")) {
//            session.sendMessage(new TextMessage(message.getPayload()));
//
//            // stop the service with the unsubscribe name or remove the session that unsubscribed
//            // be careful not to stop the service if there are still sessions available
//        } else {
//            // do something with the sent object
//
//            messagecount++;
//            // create object myMessageNumberObject = {type: 'messageNumber', messagecount: messagecount}
//            // session.sendMessage(new TextMessage(myMessageNumberObject))
//
//            // emit message with type='message'
//            session.sendMessage(new TextMessage(message.getPayload()));
//        }
//    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // the messages will be broadcasted to all users.
        System.out.println("connected");
        sessions.add(session);
    }


}