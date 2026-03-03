package init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Alumno;
import init.service.AlumnosService;


@RestController
public class AlumnosController {
	
	AlumnosService alumnosService;
	
	public AlumnosController(AlumnosService alumnosService) {
		this.alumnosService = alumnosService;
	}
	@PostMapping(value="alumnos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alta(@RequestBody Alumno alumno) {
		if(alumnosService.altaAlumno(alumno)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	@GetMapping(value="alumnos/cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> cursos() {
		return  new ResponseEntity<>(alumnosService.cursos(),HttpStatus.OK);	
	}
	@GetMapping(value="alumnos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Alumno>> alumnos() {
		return new ResponseEntity<>(alumnosService.alumnos(),HttpStatus.OK);
		
	}
	@GetMapping(value="alumnos/por-curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Alumno>> alumnoscurso(@RequestParam String curso) {
		return new ResponseEntity<>(alumnosService.alumnosCurso(curso),HttpStatus.OK);
	}
	@DeleteMapping(value="alumnos",produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Alumno> eliminar(@RequestParam String email){
		return new ResponseEntity<>(alumnosService.eliminarAlumno(email),HttpStatus.OK);
	}
}
