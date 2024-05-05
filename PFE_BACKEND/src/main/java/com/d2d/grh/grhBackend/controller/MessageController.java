package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.entity.Message;
import com.d2d.grh.grhBackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/sendNewMessage")
    public void send(@RequestBody Message message){
        this.messageService.sendNewMessage(message);
    }

    @GetMapping("/getMyMessageCandidate/{candidateId}")
    public List<Message> getMyMessageCandidate(@PathVariable int candidateId){
        return this.messageService.getMyMessageCandidate(candidateId);
    }

    @GetMapping("/getMyMessageUser/{username}")
    public List<Message> getMyMessageUser(@PathVariable String username){
        return this.messageService.getMyMessageUser(username);
    }

    @GetMapping("/loadConversation/{fromUser}/{toUser}")
    public List<Message> loadConversation(@PathVariable int fromUser, @PathVariable String toUser){
        return this.messageService.loadConversation(fromUser, toUser);
    }




}
