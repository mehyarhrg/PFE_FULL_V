package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.entity.NotificationUser;
import com.d2d.grh.grhBackend.service.NotificationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationUserController {

    @Autowired
    private NotificationUserService notificationUserService;

    @PostMapping("/pushNotificationUser")
    public String pushNotification(@RequestBody NotificationUser notificationUser){
        return this.notificationUserService.pushNotificationRequestToRh(notificationUser);
    }

    @GetMapping("/myReqNotifications/{username}")
    public List<NotificationUser> getMyNotifications(@PathVariable String username){
        return this.notificationUserService.getMyNotifications(username);
    }
}
