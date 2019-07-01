package com.luxlivesback.dto;

public class TiposTransacaoDto {
	
	private Long id;
	private String nome;
	
	public TiposTransacaoDto() {}
	
	public TiposTransacaoDto(Long id) { this.id = id; }

	public TiposTransacaoDto(Long id, String nome) {
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
		return "TiposTransacaoDto [id=" + id + ", nome=" + nome + "]";
	}

}
