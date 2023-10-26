package com.roseBanking.rosecoin.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMessage(String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@trilowbyte.com");
        message.setSubject(subject);
        message.setTo(to);
        message.setText(msg);
        mailSender.send(message);
    }
}
