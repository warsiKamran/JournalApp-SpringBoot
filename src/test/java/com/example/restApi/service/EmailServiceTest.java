package com.example.restApi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest{

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmail(){
        emailService.sendEmail("kamranwarsi.kw@gmail.com", "Testing email", "Hello, Iam Kamran");
    }
}

