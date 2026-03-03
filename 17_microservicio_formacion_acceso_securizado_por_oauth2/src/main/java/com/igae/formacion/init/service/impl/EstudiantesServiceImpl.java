package com.igae.formacion.init.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.igae.formacion.init.model.Estudiante;
import com.igae.formacion.init.model.TokenDto;
import com.igae.formacion.init.service.EstudiantesService;
@Service
public class EstudiantesServiceImpl implements EstudiantesService,InitializingBean{
	@Value("${remote.urlbase}")
	String urlBase;
	@Value("${remote.user}")
	String user;
	@Value("${remote.password}")
	String pass;
	@Value("${auth.clientId}")
	String clientId;
	@Value("${auth.grantType}")
	String grantType;
	@Value("${auth.url}")
	String authUrl;
	
	TokenDto token;
	
	RestClient restClient;
	
	public EstudiantesServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<Estudiante> estudiantesRango(double min, double max) {
		return Arrays.stream(restClient.get()
				.uri(urlBase+"/alumnos")
				.header("Authorization", "Bearer "+token.getAccess_token())
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
			.header("Authorization", "Bearer "+token.getAccess_token())
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
		MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", user);
		params.add("password", pass);
		params.add("grant_type", grantType);
		//obtenemos el token
		token= restClient.post()
					.uri(authUrl)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.body(params)
					.retrieve()
					.body(TokenDto.class);
	}

}
