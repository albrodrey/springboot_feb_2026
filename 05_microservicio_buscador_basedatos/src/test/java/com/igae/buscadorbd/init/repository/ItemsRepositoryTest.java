package com.igae.buscadorbd.init.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.igae.buscadorbd.init.model.Item;

@DataJpaTest
public class ItemsRepositoryTest {
	@Autowired
	ItemsRepository itemsRepository;
	@Test
	void testFindByTematica() {
		itemsRepository.deleteAll();

        itemsRepository.save(new Item(0, "url1", "java", "desc1"));
        itemsRepository.save(new Item(0, "url2", "java", "desc2"));
        itemsRepository.save(new Item(0, "url3", "spring", "desc3"));

        List<Item> javaItems = itemsRepository.findByTematica("java");
        //List<Item> pythonItems = itemsRepository.findByTematica("python");
        assertThat(javaItems).hasSize(2)
            .extracting(Item::getUrl)
            .containsExactlyInAnyOrder("url1", "url2");
        //assertThat(pythonItems).hasSize(0);
	}
	@Test
	void testFindFirstByUrl() {
		itemsRepository.deleteAll();

        itemsRepository.save(new Item(0, "unique-url", "testing", "desc"));

        Item found = itemsRepository.findFirstByUrl("unique-url");

        assertThat(found).isNotNull();
        assertThat(found.getTematica()).isEqualTo("testing");
	}
	@Test
	void testDeleteByUrl() {
		itemsRepository.deleteAll();

        itemsRepository.save(new Item(0, "u1", "db", "d1"));
        itemsRepository.save(new Item(0, "u2", "db", "d2"));
        itemsRepository.save(new Item(0, "u3", "other", "d3"));

        
        itemsRepository.deleteByUrl("u2");

        int countDbAfterDelete = itemsRepository.countByTematica("db");
        assertThat(countDbAfterDelete).isEqualTo(1);

        
	}
}
