package com.example.restApi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.restApi.repository.UserRepository;


@SpringBootTest
public class UserServiceTests{

    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({
        "Kamran",
        "Owais",
        "Appi"
    })
    public void findByUsernameTest(String name){
        assertNotNull(userRepository.findByuserName(name));
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "5,2,7",
        "10,10,20",
        "1,1,2"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}

