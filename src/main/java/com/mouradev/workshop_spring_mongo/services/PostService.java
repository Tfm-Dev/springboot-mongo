package com.mouradev.workshop_spring_mongo.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public List<Post> findByQuery(Map<String, String> query) {
        Arrays.asList("title", "body", "authorPost", "authorComment", "bodyComment", "minDate", "maxDate")
                .forEach(x -> query.put(x, query.get(x) != null ? URL.decodeParam(query.get(x)) : ""));
        if (query.get("minDate").equals("") || query.get("maxDate").equals(""))
            return repository.findByQuery(query.get("title"), query.get("body"), query.get("authorPost"),
                    query.get("authorComment"), query.get("bodyComment"));
        else
            return repository.findByQueryDate(query.get("title"), query.get("body"), query.get("authorPost"),
                    query.get("authorComment"), query.get("bodyComment"),
                    URL.convertDate(query.get("minDate"), new Date(0L)),
                    URL.convertDate(query.get("maxDate"), new Date()));
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
