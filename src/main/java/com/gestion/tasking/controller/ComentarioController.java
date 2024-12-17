package com.gestion.tasking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.tasking.model.ComentarioDTO;
import com.gestion.tasking.service.ComentarioService;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@PostMapping("/insertar")
	public ResponseEntity<Map<String, Object>> insertarComentario(@RequestBody ComentarioDTO comentarioDTO) {
		try {
			Map<String, Object> response = comentarioService.insertarComentario(comentarioDTO.getIdTarea(),
					comentarioDTO.getIdUsuario(), comentarioDTO.getComentario());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("mensaje", "Error: " + e.getMessage());
			errorResponse.put("codigo", 500);
			errorResponse.put("idComentario", null);
			return ResponseEntity.status(500).body(errorResponse);
		}
	}

	@PutMapping("/editar")
	public ResponseEntity<Map<String, Object>> editarComentario(@RequestBody ComentarioDTO comentarioDTO) {
		try {
			Map<String, Object> response = comentarioService.editarComentario(comentarioDTO.getIdComentario(),
					comentarioDTO.getIdTarea(), comentarioDTO.getIdUsuario(), comentarioDTO.getComentario());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("mensaje", "Error: " + e.getMessage());
			errorResponse.put("codigo", 500);
			return ResponseEntity.status(500).body(errorResponse);
		}
	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<Map<String, Object>> eliminarComentario(@RequestBody ComentarioDTO comentarioDTO) {
		try {
			Map<String, Object> response = comentarioService.eliminarComentario(comentarioDTO.getIdComentario(),
					comentarioDTO.getIdTarea(), comentarioDTO.getIdUsuario());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("mensaje", "Error: " + e.getMessage());
			errorResponse.put("codigo", 500);
			return ResponseEntity.status(500).body(errorResponse);
		}
	}

	@GetMapping("/listar/{idTarea}")
	public ResponseEntity<Map<String, Object>> listarComentariosPorTarea(@PathVariable("idTarea") Integer idTarea) {
	    try {
	        Map<String, Object> response = comentarioService.listarComentariosPorTarea(idTarea);
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("mensaje", "Error: " + e.getMessage());
	        errorResponse.put("codigo", 500);
	        return ResponseEntity.status(500).body(errorResponse);
	    }
	}

}