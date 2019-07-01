package com.luxlivesback.model;

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
@Table(name = "tipos_transacao", schema = "public")
public class TiposTransacao implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8940776762391120207L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;	
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "tipoTransacao", fetch = FetchType.LAZY, targetEntity = ComprasExtrato.class)
	private List<ComprasExtrato> comprasExtratos;
	
	public TiposTransacao() {}
	
	public TiposTransacao(Long id) { this.id = id; }

	public TiposTransacao(Long id, String nome) {
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

	public List<ComprasExtrato> getComprasExtratos() {
		return comprasExtratos;
	}

	public void setComprasExtratos(List<ComprasExtrato> comprasExtratos) {
		this.comprasExtratos = comprasExtratos;
	}

	@Override
	public String toString() {
		return "TiposTransacao [id=" + id + ", nome=" + nome + "]";
	}

}
