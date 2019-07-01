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
@Table(name = "tags", schema = "public")
public class Tags implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5942288173423093322L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "nome", unique = true, nullable = false)
	private String nome;
	
	@Column(name = "contador")
	private Long contador;
	
	@OneToMany(mappedBy = "tags", fetch = FetchType.LAZY, targetEntity = TagsLives.class)
	private List<TagsLives> tagsLives;
	
	public Tags() {}
	
	public Tags(Long id) { this.id = id; }

	public Tags(Long id, String nome, Long contador) {
		super();
		this.id = id;
		this.nome = nome;
		this.contador = contador;
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

	public Long getContador() {
		return contador;
	}

	public void setContador(Long contador) {
		this.contador = contador;
	}

	public List<TagsLives> getTagsLives() {
		return tagsLives;
	}

	public void setTagsLives(List<TagsLives> tagsLives) {
		this.tagsLives = tagsLives;
	}

	@Override
	public String toString() {
		return "Tags [id=" + id + ", nome=" + nome + ", contador=" + contador + "]";
	}		

}
