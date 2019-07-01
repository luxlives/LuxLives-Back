package com.luxlivesback.model;

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

@Entity
@Table(name = "historico_lives", schema = "public")
public class HistoricoLives implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8427351886741533581L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "dataacesso")
	private Calendar dataAcesso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lives_id")
	private Lives lives;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_streamer_id")
	private Usuarios usuariosStreamer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_espectador_id")
	private Usuarios usuariosEspectador;

	public HistoricoLives() {}
	
	public HistoricoLives(Long id) { this.id = id; }

	public HistoricoLives(Long id, Calendar dataAcesso, Lives lives, Usuarios usuariosStreamer,
			Usuarios usuariosEspectador) {
		super();
		this.id = id;
		this.dataAcesso = dataAcesso;
		this.lives = lives;
		this.usuariosStreamer = usuariosStreamer;
		this.usuariosEspectador = usuariosEspectador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Calendar dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public Lives getLives() {
		return lives;
	}

	public void setLives(Lives lives) {
		this.lives = lives;
	}

	public Usuarios getUsuariosStreamer() {
		return usuariosStreamer;
	}

	public void setUsuariosStreamer(Usuarios usuariosStreamer) {
		this.usuariosStreamer = usuariosStreamer;
	}

	public Usuarios getUsuariosEspectador() {
		return usuariosEspectador;
	}

	public void setUsuariosEspectador(Usuarios usuariosEspectador) {
		this.usuariosEspectador = usuariosEspectador;
	}

	@Override
	public String toString() {
		return "HistoricoLives [id=" + id + ", dataAcesso=" + dataAcesso + ", lives_id=" + lives.getId() + ", usuariosStreamerId="
				+ usuariosStreamer.getId() + ", usuariosEspectadorId=" + usuariosEspectador.getId() + "]";
	}		
}
