package com.igae.formacion.init.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estudiante {
	private String nombre;
	private String curso;
	private String email;
	@JsonProperty("nota")
	private double calificacion;
}
