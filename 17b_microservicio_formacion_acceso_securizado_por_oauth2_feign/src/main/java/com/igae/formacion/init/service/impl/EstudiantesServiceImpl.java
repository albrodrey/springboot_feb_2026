package com.igae.formacion.init.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.igae.formacion.init.clients.FormacionFeign;
import com.igae.formacion.init.clients.KeycloakFeign;
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
	FormacionFeign formacionFeign;
	KeycloakFeign keycloakFeign;
	

	public EstudiantesServiceImpl(RestClient restClient, FormacionFeign formacionFeign, KeycloakFeign keycloakFeign) {
		this.restClient = restClient;
		this.formacionFeign = formacionFeign;
		this.keycloakFeign = keycloakFeign;
	}

	@Override
	public List<Estudiante> estudiantesRango(double min, double max) {
		return formacionFeign.estudiantesNotas("Bearer "+token.getAccess_token()).stream()
				.filter(e->e.getCalificacion()>=min&&e.getCalificacion()<=max)
				.toList();
				
	}

	@Override
	public boolean altaEstudiante(Estudiante estudiante) {
		try {
			formacionFeign.altaEstudiante("Bearer "+token.getAccess_token(), estudiante);
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
		token=keycloakFeign.autenticar(params);
	}

}
