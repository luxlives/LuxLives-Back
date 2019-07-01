package com.luxlivesback.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "idiomas", schema = "public")
public class Idiomas implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4234567321725946361L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "lingua")
	private String lingua;
	
	@OneToMany(mappedBy = "idiomas", fetch = FetchType.LAZY, targetEntity = IdiomasUsuario.class)
	private List<IdiomasUsuario> idiomasUsuarios;
	
	public Idiomas() {}
	
	public Idiomas(Long id) { this.id = id; }

	public Idiomas(Long id, String lingua) {
		super();
		this.id = id;
		this.lingua = lingua;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public List<IdiomasUsuario> getIdiomasUsuarios() {
		return idiomasUsuarios;
	}

	public void setIdiomasUsuarios(List<IdiomasUsuario> idiomasUsuarios) {
		this.idiomasUsuarios = idiomasUsuarios;
	}

	@Override
	public String toString() {
		return "Idiomas [id=" + id + ", lingua=" + lingua + "]";
	}		
	
}
