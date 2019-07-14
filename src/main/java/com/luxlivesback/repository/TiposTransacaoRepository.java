package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.TiposTransacao;

@Transactional(readOnly = true)
public interface TiposTransacaoRepository extends JpaRepository<TiposTransacao, Long> {
	
	List<TiposTransacao> findAllByOrderByNome();
	
	TiposTransacao findByNomeIgnoreCase(String nome);
	
}
