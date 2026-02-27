package com.igae.formacion.init.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.igae.formacion.init.model.Estudiante;
@FeignClient(name="clienteAlumnos", url="${remote.urlbase}")
public interface EstudiantesFeign {
	@GetMapping("alumnos")
	List<Estudiante> estudiantes();
	@PostMapping("alumnos")
	void altaEstudiante(@RequestBody Estudiante estudiante);
}
