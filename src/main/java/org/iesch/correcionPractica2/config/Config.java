package org.iesch.correcionPractica2.config;


import org.iesch.correcionPractica2.model.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@Configuration
public class Config {

    @Bean
    public HashMap<Long, Post> inicializa(){
        HashMap<Long, Post> listaPosts = new HashMap<>();
        Post post = new Post();
        post.setId(1L);
        post.setContenido("Pagina Principal");
        post.setFecha(LocalDateTime.now());
        post.setCreador("WebMaster");
        post.setRutaImagen("http://cnd.aws.com/img.jpg");
        listaPosts.put(post.getId(), post);

        return listaPosts;

    }

    @Bean
    public List<String> listaPalabrasProhibidas(){
        List<String> palabrasProhibidas = null;

        try {
            palabrasProhibidas = Files.readAllLines(Paths.get("palabrasProhibidas.txt"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return palabrasProhibidas;
    }
}
