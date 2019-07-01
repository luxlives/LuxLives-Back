package com.luxlivesback.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(IdiomasUsuarioId.class)
@Table(name = "idiomas_usuario", schema = "public")
public class IdiomasUsuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9170051320559561561L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idioma_id", nullable = false, insertable = false, updatable = false)
	private Idiomas idiomas;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_id", nullable = false, insertable = false, updatable = false)
	private Usuarios usuarios;
	
	public IdiomasUsuario() {}

	public IdiomasUsuario(Idiomas idiomas, Usuarios usuarios) {
		super();
		this.idiomas = idiomas;
		this.usuarios = usuarios;
	}

	public Idiomas getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(Idiomas idiomas) {
		this.idiomas = idiomas;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "IdiomasUsuario [idioma_id=" + idiomas.getId() + ", usuario_id=" + usuarios.getId() + "]";
	}
	
}
