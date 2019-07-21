package com.luxlivesback.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
	
	@Column(name = "nome", unique = true, nullable = false)
	private String nome;
	
	@Column(name = "qtd_tokens")
	private Long qtdTokens;
	
	@Column(name = "preco_reais")
	private BigDecimal precoReais;
	
	@Column(name = "status", nullable = true)
	private Boolean status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "datacad", updatable = false)
	private Calendar dataCad;
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "dataalt", insertable = false)
	private Calendar dataAlt;
	
	@OneToMany(mappedBy = "produtos", fetch = FetchType.LAZY, targetEntity = ComprasExtrato.class)
	private List<ComprasExtrato> comprasExtratos;
	
	public Produtos() {}
	
	public Produtos(Long id) { this.id = id; }

	public Produtos(Long id, String nome, Long qtdTokens, BigDecimal precoReais, Boolean status, Calendar dataCad,
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

	public List<ComprasExtrato> getComprasExtratos() {
		return comprasExtratos;
	}

	public void setComprasExtratos(List<ComprasExtrato> comprasExtratos) {
		this.comprasExtratos = comprasExtratos;
	}

	@Override
	public String toString() {
		return "Produtos [id=" + id + ", nome=" + nome + ", qtdTokens=" + qtdTokens + ", precoReais=" + precoReais
				+ ", status=" + status + ", dataCad=" + dataCad + ", dataAlt=" + dataAlt + "]";
	}
	
}
