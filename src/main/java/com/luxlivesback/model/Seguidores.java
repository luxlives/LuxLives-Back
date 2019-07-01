package com.luxlivesback.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@IdClass(SeguidoresId.class)
@Table(name = "seguidores", schema = "public")
public class Seguidores implements java.io.Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5854394649665749440L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_id_seguidor", nullable = false, insertable = false, updatable = false)
	private Usuarios usuarioSeguidor;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_id_seguido", nullable = false, insertable = false, updatable = false)
	private Usuarios usuarioSeguido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "data")
	private Calendar data;
	
	public Seguidores (){}

	public Seguidores(Usuarios usuarioSeguidor, Usuarios usuarioSeguido) {
		super();
		this.usuarioSeguidor = usuarioSeguidor;
		this.usuarioSeguido = usuarioSeguido;
	}

	public Seguidores(Usuarios usuarioSeguidor, Usuarios usuarioSeguido, Calendar data) {
		super();
		this.usuarioSeguidor = usuarioSeguidor;
		this.usuarioSeguido = usuarioSeguido;
		this.data = data;
	}

	public Usuarios getUsuarioSeguidor() {
		return usuarioSeguidor;
	}

	public void setUsuarioSeguidor(Usuarios usuarioSeguidor) {
		this.usuarioSeguidor = usuarioSeguidor;
	}

	public Usuarios getUsuarioSeguido() {
		return usuarioSeguido;
	}

	public void setUsuarioSeguido(Usuarios usuarioSeguido) {
		this.usuarioSeguido = usuarioSeguido;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Seguidores [usuarioSeguidorId=" + usuarioSeguidor.getId() + ", usuarioSeguidoId=" + usuarioSeguido.getId() + ", data="
				+ data + "]";
	}	
	
}
