package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Tags;

@Transactional(readOnly = true)
public interface TagsRepository extends JpaRepository<Tags, Long> {
	
	Tags findByNomeIgnoreCase(String nome);
	
	List<Tags> findAllByOrderByNome();
	
	/**
	 * Retorna uma lista de tags em order alfabetica
	 * A busca é realizada com case sensitive e letras chaves são sinalizadas no inicio das palavras.
	 * */
	
	List<Tags> findAllByNomeStartingWithIgnoreCaseOrderByNome(String nome);	
	
}
