package com.igae.buscadorbd.init.dtos;

public class ItemDto {
	private String url;
	private String tematica;
	private String descripcion;
	public ItemDto(String url, String tematica, String descripcion) {
		super();
		this.url = url;
		this.tematica = tematica;
		this.descripcion = descripcion;
	}
	public ItemDto() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
