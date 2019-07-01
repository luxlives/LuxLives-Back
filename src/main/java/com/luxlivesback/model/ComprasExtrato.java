package com.luxlivesback.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "compras_extrato", schema = "public")
public class ComprasExtrato implements java.io.Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6118749252499787308L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "entrada")
	private Boolean entrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "data_transacao")
	private Calendar dataTransacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_id")
	private Usuarios usuarios;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produtos_id")
	private Produtos produtos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipos_transacao_id")
	private TiposTransacao tipoTransacao;
	
	public ComprasExtrato() {}
	
	public ComprasExtrato(Long id) { this.id = id; }	

	public ComprasExtrato(Long id, BigDecimal valor, Boolean entrada, Calendar dataTransacao, Usuarios usuarios, Produtos produtos,
			TiposTransacao tipoTransacao) {
		super();
		this.id = id;
		this.valor = valor;
		this.entrada = entrada;
		this.dataTransacao = dataTransacao;
		this.usuarios = usuarios;
		this.produtos = produtos;
		this.tipoTransacao = tipoTransacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getEntrada() {
		return entrada;
	}

	public void setEntrada(Boolean entrada) {
		this.entrada = entrada;
	}

	public Calendar getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Calendar dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public TiposTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TiposTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	@Override
	public String toString() {
		return "ComprasExtrato [id=" + id + ", valor=" + valor + ", entrada=" + entrada + ", dataTransacao=" + dataTransacao
				+ ", usuarios_id=" + usuarios.getId() + ", produtos_id=" + produtos.getId() + ", tipoTransacaoId=" 
				+ tipoTransacao.getId() + "]";
	}		

}
