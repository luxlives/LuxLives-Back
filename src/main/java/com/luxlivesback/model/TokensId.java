package com.luxlivesback.model;

import java.io.Serializable;

public class TokensId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3638104649034462539L;	
	
	private Long usuarios;
	
	public TokensId() {}

	public TokensId(Long usuarios) {
		super();
		
		this.usuarios = usuarios;
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
		
		TokensId other = (TokensId) obj;
		
		if (usuarios == null) {
			if (other.usuarios != null) 
				return false;
		} else if (!usuarios.equals(other.usuarios)) {
			return false;
		}
		
		return true;		
	}

}
