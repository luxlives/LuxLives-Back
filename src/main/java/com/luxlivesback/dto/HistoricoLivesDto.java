package com.luxlivesback.dto;

import java.util.Calendar;

public class HistoricoLivesDto {	
	
	private Long id;	
	private Long livesId;
	private Long usuariosStreamerId;
	private Long usuariosEspectadorId;
	private Calendar dataAcesso;
	
	public HistoricoLivesDto() {}
	
	public HistoricoLivesDto(Long id) { this.id = id; }

	public HistoricoLivesDto(Long id, Long livesId, Long usuariosStreamerId, Long usuariosEspectadorId,
			Calendar dataAcesso) {
		super();
		this.id = id;
		this.livesId = livesId;
		this.usuariosStreamerId = usuariosStreamerId;
		this.usuariosEspectadorId = usuariosEspectadorId;
		this.dataAcesso = dataAcesso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLivesId() {
		return livesId;
	}

	public void setLivesId(Long livesId) {
		this.livesId = livesId;
	}

	public Long getUsuariosStreamerId() {
		return usuariosStreamerId;
	}

	public void setUsuariosStreamerId(Long usuariosStreamerId) {
		this.usuariosStreamerId = usuariosStreamerId;
	}

	public Long getUsuariosEspectadorId() {
		return usuariosEspectadorId;
	}

	public void setUsuariosEspectadorId(Long usuariosEspectadorId) {
		this.usuariosEspectadorId = usuariosEspectadorId;
	}

	public Calendar getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Calendar dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	@Override
	public String toString() {
		return "HistoricoLivesDto [id=" + id + ", livesId=" + livesId + ", usuariosStreamerId=" + usuariosStreamerId
				+ ", usuariosEspectadorId=" + usuariosEspectadorId + ", dataAcesso=" + dataAcesso + "]";
	}	

}
