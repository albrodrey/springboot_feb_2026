package com.igae.buscador.init.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.igae.buscador.init.model.Item;
import com.igae.buscador.init.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {
	static List<Item> direcciones=new ArrayList<>(Arrays.asList(new Item("http://www.amazon.es","libros","web de libros y más cosas"),
			new Item("http://www.fnac.es","libros","libreria completa"),
			new Item("http://www.travel.es","viajes","viajes por el mundo"),
			new Item("http://www.game.es","juegos","el mundo del juego"),
			new Item("http://www.fly.com","viajes","vuelos a todos los destinos"),
			new Item("http://www.casadellibro.es","libros","libros de todos los temas")
			));
	@Override
	public List<Item> buscarPorTematica(String tematica) {
		return direcciones.stream()
				.filter(i->i.getTematica().equals(tematica))
				.toList();
	}
	@Override
	public boolean nuevoItem(Item item) {
		if(direcciones.stream().anyMatch(i->i.getUrl().equals(item.getUrl()))) {
			return false;
		}
		return direcciones.add(item);
	}

}
