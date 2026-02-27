package com.igae.buscadorbd.init.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idItem;
	@Column(name="url")
	private String url;
	private String tematica;
	private String descripcion;
	
	
}
