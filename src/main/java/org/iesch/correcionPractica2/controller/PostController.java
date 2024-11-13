package org.iesch.correcionPractica2.controller;

import org.iesch.correcionPractica2.model.Post;
import org.iesch.correcionPractica2.model.PostDTO;
import org.iesch.correcionPractica2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;

@RestController
public class PostController {

    @Autowired
    PostService postService;


    @GetMapping("/api/getPosts")
    public ResponseEntity<?> buscarTodos(){
        return postService.getAll();
    }

    @PostMapping("/admin/crearPost")
    public ResponseEntity<?> crearPost(@RequestBody PostDTO post){
        return postService.createPost(post);
    }

    @DeleteMapping("/admin/borrarPost/{id}")
    public ResponseEntity<?> borrarPost(@PathVariable long id){
        return postService.deletePost(id);
    }
    @PutMapping ("admin/updatePost/{id}")
    public ResponseEntity<?> updatePost(@PathVariable long id, @RequestBody PostDTO postDTO){
        return postService.updatePost(id, postDTO);
    }

    @GetMapping("/api/get/{id}")
    public ResponseEntity<?> get(@PathVariable long id){
        return postService.get(id);
    }
}
