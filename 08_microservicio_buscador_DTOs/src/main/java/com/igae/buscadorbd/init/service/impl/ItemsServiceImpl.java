package com.igae.buscadorbd.init.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.igae.buscadorbd.init.model.Item;
import com.igae.buscadorbd.init.repository.ItemsRepository;
import com.igae.buscadorbd.init.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

	ItemsRepository itemsRepository;
	
	public ItemsServiceImpl(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	@Override
	public List<Item> buscarPorTematica(String tematica) {
		return itemsRepository.findByTematica(tematica);
	}

	@Override
	public boolean nuevoItem(Item item) {
		if(itemsRepository.existsByUrl(item.getUrl())) {
			return false;
		}
		itemsRepository.save(item);
		return true;
	}

	@Override
	public void eliminarPorUrl(String url) {
		if(itemsRepository.existsByUrl(url)) {
			itemsRepository.deleteByUrl(url);
		}
	}

}
