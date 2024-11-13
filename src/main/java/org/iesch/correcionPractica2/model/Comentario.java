package org.iesch.correcionPractica2.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comentario {

    String creador;
    String comentario;
    LocalDateTime fecha;
}
