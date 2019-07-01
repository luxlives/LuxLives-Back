package com.luxlivesback.model;

import java.io.Serializable;

public class SeguidoresId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8143485411538154714L;
	
	private Long usuarioSeguidor;
	private Long usuarioSeguido;
	
	public SeguidoresId() {}

	public SeguidoresId(Long usuarioSeguidor, Long usuarioSeguido) {
		super();
		this.usuarioSeguidor = usuarioSeguidor;
		this.usuarioSeguido = usuarioSeguido;
	}

	public Long getUsuarioSeguidor() {
		return usuarioSeguidor;
	}

	public void setUsuarioSeguidor(Long usuarioSeguidor) {
		this.usuarioSeguidor = usuarioSeguidor;
	}

	public Long getUsuarioSeguido() {
		return usuarioSeguido;
	}

	public void setUsuarioSeguido(Long usuarioSeguido) {
		this.usuarioSeguido = usuarioSeguido;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarioSeguidor == null) ? 0 : usuarioSeguidor.hashCode());
		result = prime * result + ((usuarioSeguido == null) ? 0 : usuarioSeguido.hashCode());
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
		
		SeguidoresId other = (SeguidoresId) obj;
		
		if (usuarioSeguidor == null) {
			if (other.usuarioSeguidor != null) 
				return false;
		} else if (!usuarioSeguidor.equals(other.usuarioSeguidor)) {
			return false;
		}
		
		if (usuarioSeguido == null) {
			if (other.usuarioSeguido != null) 
				return false;
		} else if (!usuarioSeguido.equals(other.usuarioSeguido)) {
			return false;
		}
		
		return true;		
	}

}
