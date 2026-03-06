package com.igae.formacion.init.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.igae.formacion.init.model.Estudiante;
@FeignClient(name="formacionFeign",url="${remote.urlBase}")
public interface FormacionFeign {
	@GetMapping(value="alumnos",produces=MediaType.APPLICATION_JSON_VALUE)
	List<Estudiante> estudiantesNotas(@RequestHeader("Authorization") String authorization);
	@PostMapping(value="alumnos",consumes=MediaType.APPLICATION_JSON_VALUE)
	void altaEstudiante(@RequestHeader("Authorization") String authorization,@RequestBody Estudiante estudiante);
}
