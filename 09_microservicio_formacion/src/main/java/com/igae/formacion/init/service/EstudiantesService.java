package com.igae.formacion.init.service;

import java.util.List;

import com.igae.formacion.init.model.Estudiante;

public interface EstudiantesService {
	List<Estudiante> estudiantesRango(double min, double max);
	boolean altaEstudiante(Estudiante estudiante);
}
