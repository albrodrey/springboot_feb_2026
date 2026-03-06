package com.igae.formacion.init.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.igae.formacion.init.model.TokenDto;
@FeignClient(name="clienteKeycloak", url="${auth.url}")
public interface KeycloakFeign {
	@PostMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	TokenDto autenticar(@RequestBody MultiValueMap<String,String> params);
}
