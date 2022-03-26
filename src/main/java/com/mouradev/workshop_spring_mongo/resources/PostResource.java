package com.mouradev.workshop_spring_mongo.resources;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mouradev.workshop_spring_mongo.dto.PostDTO;
import com.mouradev.workshop_spring_mongo.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(x -> new PostDTO(x)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(new PostDTO(service.findById(id)));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<PostDTO>> findByQuery(@RequestParam Map<String, String> query) {
        return ResponseEntity.ok().body(
                service.findByQuery(query).stream().map(x -> new PostDTO(x)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody PostDTO post) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.insert(post.fromPostDTO()).getId()).toUri()).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable String id, @RequestBody PostDTO post) {
        return ResponseEntity.ok().body(new PostDTO(service.update(id, post.fromPostDTO())));
    }
}
