package com.igae.formacion.init.service.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.igae.formacion.init.model.CredentialsDto;
import com.igae.formacion.init.model.Estudiante;
import com.igae.formacion.init.service.EstudiantesService;

import lombok.AllArgsConstructor;
@Service
public class EstudiantesServiceImpl implements EstudiantesService,InitializingBean{
	@Value("${remote.urlbase}")
	String urlBase;
	@Value("${remote.user}")
	String user;
	@Value("${remote.password}")
	String pass;
	
	String token;
	
	RestClient restClient;
	
	public EstudiantesServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<Estudiante> estudiantesRango(double min, double max) {
		return Arrays.stream(restClient.get()
				.uri(urlBase+"/alumnos")
				.header("Authorization", "Bearer "+token)
				.retrieve()
				.body(Estudiante[].class)
				).filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
	}

	@Override
	public boolean altaEstudiante(Estudiante estudiante) {
		try {
			restClient.post()
			.uri(urlBase+"/alumnos")
			.header("Authorization", "Bearer "+token)
			.contentType(MediaType.APPLICATION_JSON)
			.body(estudiante)
			.retrieve()
			.toBodilessEntity();
			return true;
		}catch(HttpClientErrorException ex) {
			System.out.println(ex.getStatusCode());
			return false;
		}
		
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		generarToken();
		
	}
	
	private void generarToken() {
		//obtenemos el token
		token= restClient.post()
					.uri(urlBase+"/login")
					.contentType(MediaType.APPLICATION_JSON)
					.body(new CredentialsDto(user,pass))
					.retrieve()
					.body(String.class);
	}

}
