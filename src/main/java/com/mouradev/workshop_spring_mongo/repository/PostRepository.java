package com.mouradev.workshop_spring_mongo.repository;

import java.util.List;

import com.mouradev.workshop_spring_mongo.domain.Post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    
    @Query("{ 'title': {$regex: ?0, $options: 'i'}, 'author.name': {$regex: ?1, $options: 'i'} }")
    List<Post> findByQuery(String title, String name);
}