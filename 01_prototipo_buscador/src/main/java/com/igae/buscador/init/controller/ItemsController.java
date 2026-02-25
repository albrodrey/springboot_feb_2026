package com.igae.buscador.init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.igae.buscador.init.model.Item;
import com.igae.buscador.init.service.ItemsService;

@Controller
public class ItemsController {
	@Autowired
	ItemsService itemsService;
	@GetMapping(value="buscar")
	public String buscar(Model model, @RequestParam String tematica) {
		List<Item> items=itemsService.buscarPorTematica(tematica);
		model.addAttribute("listado", items); //para que sea accesible desde la vista
		return "lista";
	}
	@GetMapping(value="/")
	public String inicio() {
		return "menu";
	}
	
	@PostMapping(value="alta")
	public String altaItem(@ModelAttribute Item item,Model model) {
		if(!itemsService.nuevoItem(item)) {
			model.addAttribute("errorMsg", "No se admiten urls repetidas");
		}
		return "alta";
	}
	@GetMapping(value="go-alta")
	public String alta() {
		return "alta";
	}
	@GetMapping(value="go-buscar")
	public String busqueda() {
		return "inicio";
	}
}
