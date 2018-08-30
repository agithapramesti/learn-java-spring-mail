package com.example.mail.config;

import com.example.mail.model.MyContants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smptp.gmail.com");
        javaMailSender.setPort(587);

        javaMailSender.setUsername(MyContants.myEmail);
        javaMailSender.setPassword(MyContants.myPassword);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        
        return javaMailSender;
    }
}
