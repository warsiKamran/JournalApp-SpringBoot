package com.example.restApi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.restApi.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    
    User findByuserName(String username);

    void deleteByUserName(String username);
}

