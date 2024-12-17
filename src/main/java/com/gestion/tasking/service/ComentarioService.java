package com.gestion.tasking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class ComentarioService {

	private final static String SP_INS_COMENTARIO = "sp_insertar_comentario";
	private final static String SP_UPD_COMENTARIO = "sp_editar_comentario";
	private final static String SP_DEL_COMENTARIO = "sp_eliminar_comentario";
	private final static String SP_GET_COMENTARIO_BY_TAREA = "sp_listar_comentarios_por_tarea";

    @Autowired
    private EntityManager entityManager;

	public Map<String, Object> insertarComentario(Integer idTarea, Integer idUsuario, String comentario) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(SP_INS_COMENTARIO);
        query.setParameter("tarea_id", idTarea);
        query.setParameter("usuario_id", idUsuario);
        query.setParameter("texto_comentario", comentario);
        query.execute();

        String mensaje = (String) query.getOutputParameterValue("mensaje");
        Integer codigo = (Integer) query.getOutputParameterValue("codigo");
        Integer idComentario = (Integer) query.getOutputParameterValue("id_comentario");

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", mensaje);
        response.put("codigo", codigo);
        response.put("idComentario", idComentario);

        return response;
    }

	public Map<String, Object> editarComentario(Integer idComentario, Integer idTarea, Integer idUsuario, String comentario) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(SP_UPD_COMENTARIO);
        query.setParameter("comentario_id", idComentario);
        query.setParameter("tarea_id", idTarea);
        query.setParameter("usuario_id", idUsuario);
        query.setParameter("nuevo_texto", comentario);
        query.execute();

        String mensaje = (String) query.getOutputParameterValue("mensaje");
        Integer codigo = (Integer) query.getOutputParameterValue("codigo");

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", mensaje);
        response.put("codigo", codigo);

        return response;
	}

	public Map<String, Object> eliminarComentario(Integer idComentario, Integer idTarea, Integer idUsuario) {
	    StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(SP_DEL_COMENTARIO);

	    query.setParameter("comentario_id", idComentario);
	    query.setParameter("tarea_id", idTarea);
	    query.setParameter("usuario_id", idUsuario);
	    query.execute();

	    String mensaje = (String) query.getOutputParameterValue("mensaje");
	    Integer codigo = (Integer) query.getOutputParameterValue("codigo");

	    Map<String, Object> response = new HashMap<>();
	    response.put("mensaje", mensaje);
	    response.put("codigo", codigo);

	    return response;
	}

	public Map<String, Object> listarComentariosPorTarea(Integer idTarea) {
	    StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(SP_GET_COMENTARIO_BY_TAREA);
	    query.setParameter("tarea_id", idTarea);
	    query.execute();

	    Integer codigo = (Integer) query.getOutputParameterValue("codigo");
	    String mensaje = (String) query.getOutputParameterValue("mensaje");

	    Map<String, Object> response = new HashMap<>();
	    response.put("codigo", codigo);
	    response.put("mensaje", mensaje);

	    if (codigo == 0) {
	        List<Object[]> resultados = query.getResultList();

	        List<Map<String, Object>> comentarios = resultados.stream().map(result -> {
	            Map<String, Object> comentario = new HashMap<>();
	            comentario.put("comentario_id", result[0]);
	            comentario.put("comentario", result[1]);
	            comentario.put("fecha_comentario", result[2]);
	            comentario.put("usuario_id", result[3]);
	            comentario.put("autor", result[4]);
	            return comentario;
	        }).toList();

	        response.put("comentarios", comentarios);
	    } else {
	        response.put("comentarios", List.of());
	    }

	    return response;
	}

}