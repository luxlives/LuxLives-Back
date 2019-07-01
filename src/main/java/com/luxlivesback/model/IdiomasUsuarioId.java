package com.luxlivesback.model;

import java.io.Serializable;

public class IdiomasUsuarioId implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4570257528707408359L;
	
	private Long idiomas;
	private Long usuarios;
	
	public IdiomasUsuarioId() {}

	public IdiomasUsuarioId(Long idiomas, Long usuarios) {
		super();
		this.idiomas = idiomas;
		this.usuarios = usuarios;
	}

	public Long getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(Long idiomas) {
		this.idiomas = idiomas;
	}

	public Long getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Long usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idiomas == null) ? 0 : idiomas.hashCode());
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		IdiomasUsuarioId other = (IdiomasUsuarioId) obj;
		
		if (idiomas == null) {
			if (other.idiomas != null) 
				return false;
		} else if (!idiomas.equals(other.idiomas)) {
			return false;
		}
		
		if (usuarios == null) {
			if (other.usuarios != null) 
				return false;
		} else if (!usuarios.equals(other.usuarios)) {
			return false;
		}
		
		return true;		
	}
}
