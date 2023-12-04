package com.gty.gtyserverapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServerService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendStatusServerByEmail(boolean status){
        SimpleMailMessage message=new SimpleMailMessage();
        if(status){

            message.setFrom("abdtawil211@gmail.com");
            message.setTo("eng.abdullah.bataineh@gmail.com");
            message.setText("This Sever Is Active :)");
            message.setSubject("StatusServer");
            mailSender.send(message);
        }
        else{
            message.setFrom("eng.abdullah.bataineh@gmail.com");
            message.setTo("abdullabataineh25@gmail.com");
            message.setText("This Sever Is Not Active :(");
            message.setSubject("StatusServer");
            mailSender.send(message);
        }
    }
}
