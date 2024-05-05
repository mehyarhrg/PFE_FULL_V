package com.d2d.grh.grhBackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin("*")
public class WebSocketController {

    @MessageMapping("/notificationForLike")
    @SendTo("/topic/newNotifLike")
    public String sendNewLike(String notification) throws Exception {
        System.out.println(notification);
        return notification;

    }
}
