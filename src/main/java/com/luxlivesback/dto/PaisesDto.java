package com.luxlivesback.dto;

public class PaisesDto {
	
	private Long id;
	private String nome;
	
	public PaisesDto() {}
	
	public PaisesDto(Long id) { this.id = id; }

	public PaisesDto(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "PaisesDto [id=" + id + ", nome=" + nome + "]";
	}

}
