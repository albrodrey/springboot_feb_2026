package com.igae.buscadorbd.init.service;

import java.util.List;

import com.igae.buscadorbd.init.dtos.ItemDto;

public interface ItemsService {
	List<ItemDto> buscarPorTematica(String tematica);
	boolean nuevoItem(ItemDto item);
	void eliminarPorUrl(String url);
}
