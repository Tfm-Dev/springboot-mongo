package com.mouradev.workshop_spring_mongo.repository;

import java.util.List;

import com.mouradev.workshop_spring_mongo.domain.Post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    
    List<Post> findByTitleContaining(String txt);
}