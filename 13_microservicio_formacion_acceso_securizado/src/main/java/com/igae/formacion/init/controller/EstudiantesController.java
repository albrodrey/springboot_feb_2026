package com.igae.formacion.init.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igae.formacion.init.model.Estudiante;
import com.igae.formacion.init.service.EstudiantesService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class EstudiantesController {

	EstudiantesService estudiantesService;
	@GetMapping("estudiantes")
	public ResponseEntity<List<Estudiante>> estudiantesNota(@RequestParam double min, @RequestParam double max){
		return ResponseEntity.ok(estudiantesService.estudiantesRango(min, max));
	}
	@PostMapping("estudiantes")
	public ResponseEntity<Void> altaEstudiante(@RequestBody Estudiante estudiante){
		if(estudiantesService.altaEstudiante(estudiante)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(409).build();
	}
}
