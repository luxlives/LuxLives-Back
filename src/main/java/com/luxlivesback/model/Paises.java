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
@Table(name = "paises", schema = "public")
public class Paises implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9068691940582848009L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "paises", fetch = FetchType.LAZY, targetEntity = Usuarios.class)
	private List<Usuarios> usuarios;
	
	public Paises() {}
	
	public Paises(Long id) { this.id = id; }

	public Paises(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}	

	public Paises(Long id, String nome, List<Usuarios> usuarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuarios = usuarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Paises [id=" + id + ", nome=" + nome + "]";
	}	

}
