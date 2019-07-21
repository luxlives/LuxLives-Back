package com.luxlivesback.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.validation.constraints.NotEmpty;

public class ProdutosDto {
	
	private Long id;
	private String nome;
	private Long qtdTokens;	
	private BigDecimal precoReais;
	private Boolean status;
	private Calendar dataCad;
	private Calendar dataAlt;
	
	public ProdutosDto() {}
	
	public ProdutosDto(Long id) {this.id = id; }

	public ProdutosDto(Long id, String nome, Long qtdTokens, BigDecimal precoReais, Boolean status, Calendar dataCad,
			Calendar dataAlt) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtdTokens = qtdTokens;
		this.precoReais = precoReais;
		this.status = status;
		this.dataCad = dataCad;
		this.dataAlt = dataAlt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome não pode ser vazio")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQtdTokens() {
		return qtdTokens;
	}

	public void setQtdTokens(Long qtdTokens) {
		this.qtdTokens = qtdTokens;
	}

	public BigDecimal getPrecoReais() {
		return precoReais;
	}

	public void setPrecoReais(BigDecimal precoReais) {
		this.precoReais = precoReais;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Calendar getDataCad() {
		return dataCad;
	}

	public void setDataCad(Calendar dataCad) {
		this.dataCad = dataCad;
	}

	public Calendar getDataAlt() {
		return dataAlt;
	}

	public void setDataAlt(Calendar dataAlt) {
		this.dataAlt = dataAlt;
	}

	@Override
	public String toString() {
		return "ProdutosDto [id=" + id + ", nome=" + nome + ", qtdTokens=" + qtdTokens + ", precoReais=" + precoReais
				+ ", status=" + status + ", dataCad=" + dataCad + ", dataAlt=" + dataAlt + "]";
	}	

}
