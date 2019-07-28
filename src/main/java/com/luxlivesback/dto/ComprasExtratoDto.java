package com.luxlivesback.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.validation.constraints.NotNull;

public class ComprasExtratoDto {
	
	private Long id;
	private BigDecimal valor;
	private Boolean entrada;
	private Calendar dataTransacao;
	private Long usuariosId;
	private Long produtosId;
	private Long tipoTransacaoId;
	
	public ComprasExtratoDto() {}
	
	public ComprasExtratoDto(Long id) { this.id = id; }

	public ComprasExtratoDto(Long id, BigDecimal valor, Boolean entrada, Calendar dataTransacao, Long usuariosId,
			Long produtosId, Long tipoTransacaoId) {
		super();
		this.id = id;
		this.valor = valor;
		this.entrada = entrada;
		this.dataTransacao = dataTransacao;
		this.usuariosId = usuariosId;
		this.produtosId = produtosId;
		this.tipoTransacaoId = tipoTransacaoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "Valor não pode ser vazio")
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@NotNull(message = "Entrada não pode ser vazio")
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

	@NotNull(message = "Usuarios_Id não pode ser vazio")
	public Long getUsuariosId() {
		return usuariosId;
	}

	public void setUsuariosId(Long usuariosId) {
		this.usuariosId = usuariosId;
	}

	@NotNull(message = "Produtos_Id não pode ser vazio")
	public Long getProdutosId() {
		return produtosId;
	}

	public void setProdutosId(Long produtosId) {
		this.produtosId = produtosId;
	}

	@NotNull(message = "TipoTransacao_Id não pode ser vazio")
	public Long getTipoTransacaoId() {
		return tipoTransacaoId;
	}

	public void setTipoTransacaoId(Long tipoTransacaoId) {
		this.tipoTransacaoId = tipoTransacaoId;
	}

	@Override
	public String toString() {
		return "ComprasExtratoDto [id=" + id + ", valor=" + valor + ", entrada=" + entrada + ", dataTransacao="
				+ dataTransacao + ", usuariosId=" + usuariosId + ", produtosId=" + produtosId + ", tipoTransacaoId="
				+ tipoTransacaoId + "]";
	}

}
