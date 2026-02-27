package com.igae.buscadorbd.init.controller;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igae.buscadorbd.init.model.Item;
import com.igae.buscadorbd.init.service.ItemsService;

@WebMvcTest(ItemsController.class)
public class TestItemsController {
    @MockBean
    private ItemsService itemsService;  
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void testBuscarPorTematica() throws Exception {
        List<Item> items = Arrays.asList(
                new Item(1, "u1", "java", "d1"),
                new Item(2, "u2", "java", "d2")
            );

            when(itemsService.buscarPorTematica("java")).thenReturn(items);

            mockMvc.perform(get("/v1/items").param("tematica", "java"))
                .andExpect(status().isOk())             
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].url").value("u1"));
    }
    
    @Test
    void altaItem() throws Exception {
        Item item = new Item(null, "unique-url", "testing", "desc");

        when(itemsService.nuevoItem(any(Item.class))).thenReturn(true);

        mockMvc.perform(post("/v1/items")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(item)))
            .andExpect(status().isCreated());
    }
    @Test
    void altaItemNoOk() throws Exception {
        Item item = new Item(null, "unique-url", "testing", "desc");

        when(itemsService.nuevoItem(any(Item.class))).thenReturn(false);

        mockMvc.perform(post("/v1/items")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(item)))
            .andExpect(status().isConflict());
    }
}