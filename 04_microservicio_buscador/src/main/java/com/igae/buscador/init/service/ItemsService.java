package com.igae.buscador.init.service;

import java.util.List;

import com.igae.buscador.init.model.Item;

public interface ItemsService {
	List<Item> buscarPorTematica(String tematica);
	boolean nuevoItem(Item item);
	void eliminarPorUrl(String url);
}
