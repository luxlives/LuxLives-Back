package com.luxlivesback.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "lives", schema = "public")
public class Lives implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6655962324129816098L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "genero")
	private String genero;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "dataini", updatable = false)
	private Calendar dataIni;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datafim")
	private Calendar dataFim;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarios_id", nullable = false, updatable = false)
	private Usuarios usuarios;
	
	@OneToMany(mappedBy = "lives", fetch = FetchType.LAZY, targetEntity = TagsLives.class)
	private List<TagsLives> tagsLives;
	
	@OneToMany(mappedBy = "lives", fetch = FetchType.LAZY, targetEntity = HistoricoLives.class)
	private List<HistoricoLives> historicosLives;
	
	public Lives() {}
	
	public Lives(Long id) { this.id = id; }

	public Lives(Long id, String titulo, String genero, Calendar dataIni, Calendar dataFim, Usuarios usuarios) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.usuarios = usuarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Calendar getDataIni() {
		return dataIni;
	}

	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public List<TagsLives> getTagsLives() {
		return tagsLives;
	}

	public void setTagsLives(List<TagsLives> tagsLives) {
		this.tagsLives = tagsLives;
	}

	public List<HistoricoLives> getHistoricosLives() {
		return historicosLives;
	}

	public void setHistoricosLives(List<HistoricoLives> historicosLives) {
		this.historicosLives = historicosLives;
	}

	@Override
	public String toString() {
		return "Lives [id=" + id + ", titulo=" + titulo + ", genero=" + genero + ", dataIni=" + dataIni + ", dataFim="
				+ dataFim + ", usuarios_id=" + usuarios.getId() + "]";
	}	

}
