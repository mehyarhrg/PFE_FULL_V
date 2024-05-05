package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.converter.CandidacyConverter;
import com.d2d.grh.grhBackend.converter.CandidateConverter;
import com.d2d.grh.grhBackend.converter.UserConverter;
import com.d2d.grh.grhBackend.dto.CandidateDTO;
import com.d2d.grh.grhBackend.dto.UserDto;
import com.d2d.grh.grhBackend.entity.Candidate;
import com.d2d.grh.grhBackend.entity.Message;
import com.d2d.grh.grhBackend.repository.CandidateRepository;
import com.d2d.grh.grhBackend.repository.MessageRepository;
import com.d2d.grh.grhBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateConverter candidateConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;


    public void sendNewMessage(Message message){
        this.messageRepository.save(message);
    }

    public List<Message> getMyMessageCandidate(int candidateId){
        CandidateDTO candidate = this.candidateConverter.entityToDto(this.candidateRepository.findById(candidateId).get());
        List<Message> candidateMessage = this.messageRepository.findAll().stream()
                .filter(message -> message.getToUser() == candidate.getCandidateId())
                .collect(Collectors.toList());
        return candidateMessage;
    }

    public List<Message> getMyMessageUser(String username){
        UserDto user = this.userService.findUserByUsername(username);
        List<Message> userMessage = this.messageRepository.findAll().stream()
                .filter(message -> message.getToUser() == user.getUserId().intValue())
                .collect(Collectors.toList());
        return userMessage;
    }

    public List<Message> loadConversation(int fromUser, String toUser){
        UserDto user = this.userService.findUserByUsername(toUser);

//        UserDto user = this.userConverter.entityToDto(this.userRepository.findById(toUser).get());
        CandidateDTO candidateDTO = this.candidateConverter.entityToDto(this.candidateRepository.findById(fromUser).get());
        List<Message> conversation = this.messageRepository.findAll().stream()
                .filter(message -> message.getFromUser().equals(candidateDTO.getCandidateId())  && message.getToUser() == user.getUserId().intValue())
                .collect(Collectors.toList());
        return conversation;
    }
}
