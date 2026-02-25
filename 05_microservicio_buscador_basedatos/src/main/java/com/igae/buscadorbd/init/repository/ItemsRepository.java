package com.igae.buscadorbd.init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.igae.buscadorbd.init.model.Item;

public interface ItemsRepository extends JpaRepository<Item, Integer> {
	List<Item> findByTematica(String tematica);
	Item findFirstByUrl(String url);
	boolean existsByUrl(String url);
	@Transactional
	@Modifying
	void deleteByUrl(String url);
	@Query("select i from Item i where i.descripcion like %?1%")
	List<Item> findByDesc(String word);
	@Query(value="select count(*) from items where tematica=?",nativeQuery = true)
	int countByTematica(String tematica);
}
