package com.luxlivesback.dto;

public class IdiomasDto {
	
	private Long id;
	private String lingua;
	
	public IdiomasDto() {}
	
	public IdiomasDto(Long id) {this.id = id; }

	public IdiomasDto(Long id, String lingua) {
		super();
		this.id = id;
		this.lingua = lingua;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	@Override
	public String toString() {
		return "IdiomasDto [id=" + id + ", lingua=" + lingua + "]";
	}

}
