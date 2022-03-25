package com.mouradev.workshop_spring_mongo.services;

import java.util.List;

import com.mouradev.workshop_spring_mongo.domain.User;
import com.mouradev.workshop_spring_mongo.repository.UserRepository;
import com.mouradev.workshop_spring_mongo.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found with id!"));
    }
}
