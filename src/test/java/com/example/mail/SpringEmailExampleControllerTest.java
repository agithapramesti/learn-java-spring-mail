package com.example.mail;

import com.example.mail.controller.SpringEmailExampleController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpringEmailExampleControllerTest {

    private static final String from = "gegetest366@gmail.com";
    private static final String replayTo = null;
    private static final List<String> to = Arrays.asList(
                                "agithapramesti.s.m@gmail.com",
                                "jason.alexander@gdn-commerce.com");

    private static final Date sentDate = null;
    private static final String subject = "Learn Make Simple Email";
    private static final String text = "Hai There!";

    private MockMvc mockMvc;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SpringEmailExampleController springEmailExampleController;

    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(springEmailExampleController) //Build a MockMvc instance by registering one or more class
                .build();
    }

    @After
    public void tearDown(){

        verifyNoMoreInteractions(javaMailSender);
    }

    @Test
    public void testsendSimpleEmail()throws Exception{

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setText(text);
        mailMessage.setSubject(subject);
        mailMessage.setTo(to.get(0), to.get(1));

        mailMessage.setReplyTo(replayTo);
        mailMessage.setSentDate(sentDate);
        
        this.mockMvc.perform(get("/send-mail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("from", equalTo(from)))
                .andExpect(jsonPath("replyTo", equalTo(replayTo)))
                .andExpect(jsonPath("to", equalTo(to)))
                .andExpect(jsonPath("sentDate", equalTo(sentDate)))
                .andExpect(jsonPath("subject", equalTo(subject)))
                .andExpect(jsonPath("text", equalTo(text)));

        verify(javaMailSender, times(1)).send(mailMessage);
    }

}
