package com.example.restApi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest{

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void run(){
        redisTemplate.opsForValue().set("email", "kamran@gmail.com");
        Object name = redisTemplate.opsForValue().get("name");
        int a = 1;
    }
}

