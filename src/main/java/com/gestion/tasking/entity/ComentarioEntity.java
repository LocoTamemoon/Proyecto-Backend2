package com.gestion.tasking.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "sp_insertar_comentario", procedureName = "sp_insertar_comentario", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "tarea_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "usuario_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "texto_comentario", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "mensaje", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "codigo", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "id_comentario", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "sp_editar_comentario", procedureName = "sp_editar_comentario", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "comentario_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "tarea_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "usuario_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nuevo_texto", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "mensaje", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "codigo", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "sp_eliminar_comentario", procedureName = "sp_eliminar_comentario", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "comentario_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "tarea_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "usuario_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "mensaje", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "codigo", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "sp_listar_comentarios_por_tarea", procedureName = "sp_listar_comentarios_por_tarea", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "tarea_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "codigo", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "mensaje", type = String.class) }) })
@AllArgsConstructor
@Data
public class ComentarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idComentario;

	private String textoComentario;

	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

}