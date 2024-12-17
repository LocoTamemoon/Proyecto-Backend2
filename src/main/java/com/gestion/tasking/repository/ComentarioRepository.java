package com.gestion.tasking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.gestion.tasking.entity.ComentarioEntity;
import com.gestion.tasking.model.ResponseDTO;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Integer> {

    @Procedure(name = "sp_insertar_comentario")
    ResponseDTO spInsertarComentario(
        @Param("tarea_id") Integer idUsuario,
        @Param("usuario_id") Integer idPrioridad,
        @Param("texto_comentario") String nombreProyecto,
        @Param("codigo") Integer codigo,
        @Param("mensaje") String mensaje,
        @Param("id_comentario") Integer idProyectoGenerado
    );


}