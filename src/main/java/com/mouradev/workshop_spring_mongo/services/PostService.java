package com.mouradev.workshop_spring_mongo.services;

import java.util.List;

import com.mouradev.workshop_spring_mongo.domain.Post;
import com.mouradev.workshop_spring_mongo.repository.PostRepository;
import com.mouradev.workshop_spring_mongo.resources.util.URL;
import com.mouradev.workshop_spring_mongo.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    private PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found with id!"));
    }

    public List<Post> findByTitle(String txt) {
        return repository.findByTitleContaining(URL.decodeParam(txt));
    }

    public Post insert(Post user) {
        return repository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Post update(String id, Post user) {
        findById(id);
        user.setId(id);
        return repository.save(user);
    }
}
