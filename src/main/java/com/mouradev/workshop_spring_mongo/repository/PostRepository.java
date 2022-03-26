package com.mouradev.workshop_spring_mongo.repository;

import java.util.Date;
import java.util.List;

import com.mouradev.workshop_spring_mongo.domain.Post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'title' : { $regex: ?0, $options: 'i' }, "
            + "'body' : { $regex: ?1, $options: 'i' }, "
            + "'author.name' : { $regex: ?2, $options: 'i' }, "
            + "'comments.author.name' : { $regex : ?3, $options: 'i' }, "
            + "'comments.text' : { $regex : ?4, $options: 'i' }}")
    List<Post> findByQuery(String title, String body, String authorPost, String authorComment, String bodyComment);

    @Query("{ $and : [ {'title' : { $regex: ?0, $options: 'i' }, "
            + "'body' : { $regex: ?1, $options: 'i' }, "
            + "'author.name' : { $regex: ?2, $options: 'i' }, "
            + "'comments.author.name' : { $regex : ?3, $options: 'i' }, "
            + "'comments.text' : { $regex : ?4, $options: 'i' }}, "
            + "{ date: { $gte : ?5 } }, { date: { $lte : ?6 } } ]}")
    List<Post> findByQueryDate(String title, String body, String authorPost, String authorComment, String bodyComment,
            Date minDate, Date maxDate);
}