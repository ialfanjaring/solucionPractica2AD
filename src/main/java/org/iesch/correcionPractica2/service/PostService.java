package org.iesch.correcionPractica2.service;

import org.iesch.correcionPractica2.model.Post;
import org.iesch.correcionPractica2.model.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;

@Service
public class PostService {
    @Autowired
    HashMap<Long, Post> listaPost;

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(listaPost);
    }

    public ResponseEntity<?> createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setCreador(postDTO.getCreador());
        post.setRutaImagen(postDTO.getRutaImagen());
        post.setContenido(postDTO.getContenido());
        post.setFecha(LocalDateTime.now());

        Long nextKey = listaPost.isEmpty() ? 1 : Collections.max(
                listaPost.keySet()) + 1;

        post.setId(nextKey);
        listaPost.put(nextKey, post);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);

    }

    public ResponseEntity<?> deletePost(long id) {

        listaPost.remove(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> updatePost(long id, PostDTO postDTO) {
        if (listaPost.containsKey(id)){
            Post post = listaPost.get(id);
            post.setCreador(postDTO.getCreador());
            post.setRutaImagen(postDTO.getRutaImagen());
            post.setFecha(LocalDateTime.now());
            post.setContenido(postDTO.getContenido());

            return ResponseEntity.ok(post);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> get(long id) {
        Post post = listaPost.get(id);

        return ResponseEntity.ok(post);
    }
}
