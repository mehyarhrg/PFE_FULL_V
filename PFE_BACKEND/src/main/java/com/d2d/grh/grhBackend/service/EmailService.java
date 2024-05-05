package com.d2d.grh.grhBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mihyar999@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        this.mailSender.send(message);
        System.out.println("Mail sent successfully");
    }
}
