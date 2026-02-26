package com.igae.buscadorbd.init.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.igae.buscadorbd.init.model.Item;
import com.igae.buscadorbd.init.service.ItemsService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(ItemsController.class)
public class TestItemsController {
	@MockBean
    private ItemsService itemsService;  
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void testBuscarPorTematica() {
    	List<Item> items = Arrays.asList(
                new Item(1, "u1", "java", "d1"),
                new Item(2, "u2", "java", "d2")
            );

            when(itemsService.buscarPorTematica("java")).thenReturn(items);

            mockMvc.perform(get("/items").param("tematica", "java"))
                .andExpect(status().isOk())             
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].url").value("u1"));
    }
    
    @Test
    void altaItem() {
    	Item item = new Item(0, "unique-url", "testing", "desc");

        when(itemsService.nuevoItem(item)).thenReturn(true);

        mockMvc.perform(post("/items")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(item)))
            .andExpect(status().isOk());
    }
    @Test
    void altaItemNoOk() {
    	Item item = new Item(0, "unique-url", "testing", "desc");

        when(itemsService.nuevoItem(item)).thenReturn(false);

        mockMvc.perform(post("/items")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(item)))
            .andExpect(status().is(409));
    }
}
