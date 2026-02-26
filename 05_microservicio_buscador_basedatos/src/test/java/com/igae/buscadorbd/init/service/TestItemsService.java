package com.igae.buscadorbd.init.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.igae.buscadorbd.init.model.Item;
import com.igae.buscadorbd.init.repository.ItemsRepository;
import com.igae.buscadorbd.init.service.impl.ItemsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestItemsService {
	@Mock
	ItemsRepository itemsRepository;
	@InjectMocks
	ItemsServiceImpl itemsService;

	@Test
	void testNumevoItem() {
		Item item = new Item(0, "unique-url", "testing", "desc");

        when(itemsRepository.existsByUrl("unique-url")).thenReturn(false);
        when(itemsRepository.save(item)).thenReturn(item);

        boolean created = itemsService.nuevoItem(item);

        assertThat(created).isTrue();
        verify(itemsRepository).existsByUrl("unique-url");
        verify(itemsRepository).save(item);
	}
}
