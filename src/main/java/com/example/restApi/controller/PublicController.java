package com.example.restApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restApi.entity.User;
import com.example.restApi.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController{

    @Autowired
    private UserService userService;

    @PostMapping("create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}

