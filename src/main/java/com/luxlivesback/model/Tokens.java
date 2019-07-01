package com.luxlivesback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tokens", schema = "public")
public class Tokens implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4830426708148415073L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_id", nullable = false, insertable = false, updatable = false)
	private Usuarios usuarios;
	
	@Column(name = "qtd_token")
	private Long qtdToken;
	
	public Tokens() {}
	
	public Tokens(Long id) { this.id = id;}	

	public Tokens(Long id, Usuarios usuarios, Long qtdToken) {
		super();
		this.id = id;
		this.usuarios = usuarios;
		this.qtdToken = qtdToken;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Long getQtdToken() {
		return qtdToken;
	}

	public void setQtdToken(Long qtdToken) {
		this.qtdToken = qtdToken;
	}

	@Override
	public String toString() {
		return "Tokens [id=" + id + ", usuario_id=" + usuarios.getId() + ", qtdToken=" + qtdToken + "]";
	}
	
}
