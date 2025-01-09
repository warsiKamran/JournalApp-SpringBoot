package com.example.restApi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.restApi.entity.User;
import com.example.restApi.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component
public class UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User userEntry){
        userRepository.save(userEntry);
    }

    public void saveNewUser(User userEntry){
        
        try{
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER"));
            userRepository.save(userEntry);
        } 
        
        catch (Exception e){
            logger.error("error occured", e);
            logger.info("giving info");
            logger.warn("warning generated", e);
        }
    }

    public void saveAdmin(User userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(userEntry);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByuserName(String userName){
        return userRepository.findByuserName(userName);
    }
}

