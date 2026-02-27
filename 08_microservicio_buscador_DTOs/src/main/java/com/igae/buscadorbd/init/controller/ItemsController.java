package com.igae.buscadorbd.init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igae.buscadorbd.init.dtos.ItemDto;
import com.igae.buscadorbd.init.model.Item;
import com.igae.buscadorbd.init.service.ItemsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RequestMapping(value="v1")
@RestController
public class ItemsController {
	//@Autowired
	ItemsService itemsService;
	public ItemsController(ItemsService itemsService) {
		this.itemsService=itemsService;
	}
	@Operation(summary = "Lista de items por temática",description = "A partir de la temática recibida como prámetro, devuelve los items correspondientes")
	@GetMapping(value="items",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemDto>> buscarPorTematica(@Parameter(description = "Nombre de la temática de búsqueda") @RequestParam String tematica){
		return new ResponseEntity<>(itemsService.buscarPorTematica(tematica),HttpStatus.OK);
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Alumno creado correctamente", content = @Content),
		@ApiResponse(responseCode = "409", description = "No se pudo crear el alumno (conflicto)", content = @Content)
	})
	@PostMapping(value="items",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaItem(@RequestBody ItemDto item) {
		if(itemsService.nuevoItem(item)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	@DeleteMapping(value="items")
	public ResponseEntity<Void> eliminarItem(@RequestParam String url) {
		 itemsService.eliminarPorUrl(url);
		 return ResponseEntity.ok().build();
	}
}
