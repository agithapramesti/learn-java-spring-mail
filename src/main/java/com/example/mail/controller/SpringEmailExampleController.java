package com.example.mail.controller;

import com.example.mail.model.MyContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-mail")
public class SpringEmailExampleController {

    @Autowired
    public JavaMailSender javaMailSender;


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    SimpleMailMessage sendSimpleEmail(){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyContants.friendEmail, MyContants.koJason);
        message.setFrom(MyContants.myEmail);
        message.setSubject("Learn Make Simple Email");
        message.setText("Hai There!");

        // Send Message!
        this.javaMailSender.send(message);

        return message;
    }
}
