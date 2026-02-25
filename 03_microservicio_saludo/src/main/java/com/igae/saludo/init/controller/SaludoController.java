package com.igae.saludo.init.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igae.saludo.init.model.Ficha;

@RestController
public class SaludoController {
	@GetMapping(value="saludar")
	public String saludo() {
		return "Bienvenido a REST";
	}
	@GetMapping(value="saludar/{name}",produces=MediaType.TEXT_PLAIN_VALUE)
	public String saludo(@PathVariable String name) {
		return "Bienvenido a REST sr./a "+name;
	}
	@GetMapping(value="saludar/completo")
	public String saludo(@RequestParam String name,@RequestParam int age) {
		return "Bienvenido a REST "+name+" tienes "+age+" años";
	}
	@GetMapping(value="ficha",produces=MediaType.APPLICATION_JSON_VALUE)
	public Ficha buscarFicha(@RequestParam String name) {
		return new Ficha(name,"prueba@gmail.com",23);
	}
	@PostMapping(value="ficha",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void altaFicha(@RequestBody Ficha ficha) {
		System.out.println("Nombre: "+ficha.getName()+" Email:"+ficha.getEmail());
	}
}
