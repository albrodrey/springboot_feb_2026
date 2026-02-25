package com.igae.buscador.init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.igae.buscador.init.model.Item;
import com.igae.buscador.init.service.ItemsService;

import jakarta.validation.Valid;
@RequestMapping(value="v1")
@RestController
public class ItemsController {
	//@Autowired
	ItemsService itemsService;
	public ItemsController(ItemsService itemsService) {
		this.itemsService=itemsService;
	}
	@GetMapping(value="items",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> buscarPorTematica(@RequestParam String tematica){
		return new ResponseEntity<>(itemsService.buscarPorTematica(tematica),HttpStatus.OK);
	}
	@PostMapping(value="items",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaItem(@Valid @RequestBody Item item) {
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
