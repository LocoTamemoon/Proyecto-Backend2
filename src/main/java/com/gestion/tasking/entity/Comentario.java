package com.gestion.tasking.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tm_comentarios")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	@JsonProperty("idComentario")
	private Integer idComentario;

	@Column(name = "texto_comentario")
	@JsonProperty("textoComentario")
	private String textoComentario;

	@Column(name = "fecha_creacion")
	@JsonProperty("fechaCreacion")
	private LocalDate fechaCreacion;

}