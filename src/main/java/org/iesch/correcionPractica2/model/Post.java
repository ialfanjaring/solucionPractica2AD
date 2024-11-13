package org.iesch.correcionPractica2.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Post {
    private long id;
    private String creador;
    private String contenido;
    private String rutaImagen;
    private LocalDateTime fecha;
    List<Comentario> comentarios;
}
