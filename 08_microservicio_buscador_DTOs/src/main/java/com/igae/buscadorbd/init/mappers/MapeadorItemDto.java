package com.igae.buscadorbd.init.mappers;

import org.springframework.stereotype.Component;

import com.igae.buscadorbd.init.dtos.ItemDto;
import com.igae.buscadorbd.init.model.Item;

@Component
public class MapeadorItemDto {

	public ItemDto toDto(Item item) {
		return new ItemDto(item.getUrl(),item.getTematica(),item.getDescripcion());
	}
	
	public Item toEntity(ItemDto item) {
		return new Item(0,item.getUrl(),item.getTematica(),item.getDescripcion());
	}
}
