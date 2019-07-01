package com.luxlivesback.dto;

public class TagsDto {
	
	private Long id;
	private String nome;
	private Long contador;
	
	public TagsDto() {}
	
	public TagsDto(Long id) { this.id = id; }

	public TagsDto(Long id, String nome, Long contador) {
		super();
		this.id = id;
		this.nome = nome;
		this.contador = contador;
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

	public Long getContador() {
		return contador;
	}

	public void setContador(Long contador) {
		this.contador = contador;
	}

	@Override
	public String toString() {
		return "TagsDto [id=" + id + ", nome=" + nome + ", contador=" + contador + "]";
	}
	
}
