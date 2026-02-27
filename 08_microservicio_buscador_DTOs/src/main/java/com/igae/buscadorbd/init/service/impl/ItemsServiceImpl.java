package com.igae.buscadorbd.init.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.igae.buscadorbd.init.dtos.ItemDto;
import com.igae.buscadorbd.init.mappers.MapeadorItemDto;
import com.igae.buscadorbd.init.repository.ItemsRepository;
import com.igae.buscadorbd.init.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

	ItemsRepository itemsRepository;
	MapeadorItemDto mapper;
	public ItemsServiceImpl(ItemsRepository itemsRepository,MapeadorItemDto mapper) {
		this.itemsRepository = itemsRepository;
		this.mapper=mapper;
	}

	@Override
	public List<ItemDto> buscarPorTematica(String tematica) {
		return itemsRepository.findByTematica(tematica).stream()
				.map(mapper::toDto) //Stream<ItemDto>
				.toList();
				
	}

	@Override
	public boolean nuevoItem(ItemDto item) {
		if(itemsRepository.existsByUrl(item.getUrl())) {
			return false;
		}
		itemsRepository.save(mapper.toEntity(item));
		return true;
	}

	@Override
	public void eliminarPorUrl(String url) {
		if(itemsRepository.existsByUrl(url)) {
			itemsRepository.deleteByUrl(url);
		}
	}

}
