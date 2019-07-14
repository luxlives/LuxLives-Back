package com.luxlivesback.dto;

import java.util.Calendar;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LivesDto {	
	
	private Long id;
	private String titulo;
	private String genero;
	private Calendar dataIni;
	private Calendar dataFim;
	private Long usuarioId;
	
	public LivesDto() {}
	
	public LivesDto(Long id) { this.id = id; }	

	public LivesDto(Long id, String titulo, String genero, Calendar dataIni, Calendar dataFim, Long usuarioId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.usuarioId = usuarioId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Título não pode ser vazio")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@NotEmpty(message = "Genero não pode ser vazio")
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

	@NotNull(message = "Usuario_Id não pode ser vazio")
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public String toString() {
		return "LivesDto [id=" + id + ", titulo=" + titulo + ", genero=" + genero + ", dataIni=" + dataIni
				+ ", dataFim=" + dataFim + ", usuarioId=" + usuarioId + "]";
	}	

}
