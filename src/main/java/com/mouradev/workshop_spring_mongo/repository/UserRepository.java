package com.mouradev.workshop_spring_mongo.repository;

import com.mouradev.workshop_spring_mongo.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
}