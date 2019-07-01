package com.luxlivesback.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produtos", schema = "public")
public class Produtos implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1434342365972611817L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "qtd_tokens")
	private Long qtdTokens;
	
	@Column(name = "preco_reais", nullable = true)
	private BigDecimal precoReais;
	
	@OneToMany(mappedBy = "produtos", fetch = FetchType.LAZY, targetEntity = ComprasExtrato.class)
	private List<ComprasExtrato> comprasExtratos;
	
	public Produtos() {}
	
	public Produtos(Long id) { this.id = id; }

	public Produtos(Long id, Long qtdTokens, BigDecimal precoReais) {
		super();
		this.id = id;
		this.qtdTokens = qtdTokens;
		this.precoReais = precoReais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ComprasExtrato> getComprasExtratos() {
		return comprasExtratos;
	}

	public void setComprasExtratos(List<ComprasExtrato> comprasExtratos) {
		this.comprasExtratos = comprasExtratos;
	}

	@Override
	public String toString() {
		return "Produtos [id=" + id + ", qtdTokens=" + qtdTokens + ", precoReais=" + precoReais + "]";
	}	

}
