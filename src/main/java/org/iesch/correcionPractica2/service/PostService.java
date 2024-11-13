package org.iesch.correcionPractica2.service;

import org.iesch.correcionPractica2.model.Comentario;
import org.iesch.correcionPractica2.model.CommentDTO;
import org.iesch.correcionPractica2.model.Post;
import org.iesch.correcionPractica2.model.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PostService {
    @Autowired
    HashMap<Long, Post> listaPost;

    @Autowired
    List<String> palabrasProhibidas;

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

    public ResponseEntity<?> comentar(long id, CommentDTO commentDTO) {

        if(contienePalabraProhibida(commentDTO.getComentario())){
            commentDTO.setComentario("El comentario no se publico por Palabra Mal Sonante");
            return ResponseEntity.ok(commentDTO);
        }else {
            Post post = listaPost.get(id);
            Comentario comentario = new Comentario();
            comentario.setComentario(commentDTO.getComentario());
            comentario.setFecha(LocalDateTime.now());
            comentario.setCreador(commentDTO.getCreador());
            post.getComentarios().add(comentario);

            return ResponseEntity.ok(post);

        }


    }

    private boolean contienePalabraProhibida(String comentario) {
        for (String palabraProhibida : palabrasProhibidas){
            String pattherString = "\\b" + Pattern.quote(comentario) + "\\b";
            if(palabraProhibida.toLowerCase().matches(pattherString.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
