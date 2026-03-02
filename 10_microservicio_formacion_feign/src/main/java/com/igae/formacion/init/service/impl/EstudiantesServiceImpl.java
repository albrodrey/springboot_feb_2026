package com.igae.formacion.init.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.igae.formacion.init.model.Estudiante;
import com.igae.formacion.init.service.EstudiantesFeign;
import com.igae.formacion.init.service.EstudiantesService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EstudiantesServiceImpl implements EstudiantesService {
	EstudiantesFeign estudiantesFeign;
	
	

	@Override
	public List<Estudiante> estudiantesRango(double min, double max) {
		if(min>max) {
			throw new IllegalArgumentException();
		}
		
		return estudiantesFeign.estudiantes().stream()
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
				
	}

	@Override
	public boolean altaEstudiante(Estudiante estudiante) {
		try {
			estudiantesFeign.altaEstudiante(estudiante);
			return true;
		}catch(HttpClientErrorException ex) {
			System.out.println(ex.getStatusCode());
			return false;
		}
		
	}

}
