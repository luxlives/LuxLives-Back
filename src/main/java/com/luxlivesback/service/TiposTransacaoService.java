package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import com.luxlivesback.model.TiposTransacao;

public interface TiposTransacaoService {
	
	Optional<List<TiposTransacao>> findAll();
	
	Optional<List<TiposTransacao>> findAllByOrderByNome();
	
	Optional<TiposTransacao> findById(Long id);
	
	Optional<TiposTransacao> findByNomeIgnoreCase(String nome);
	
	TiposTransacao save(TiposTransacao tiposTransacao);
	
	Boolean deleteById(Long id);

}
