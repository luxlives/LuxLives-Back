package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.luxlivesback.model.Produtos;

public interface ProdutosService {
	
	Optional<List<Produtos>> findAll();
	
	Optional<List<Produtos>> findAllByStatus(Boolean status);
	
	Optional<List<Produtos>> findAllByStatusOrderByIdAscByPage(Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByStatusOrderByNomeAscByPage(Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByStatusOrderByNomeDescByPage(Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByStatusOrderByQtdTokensAscByPage(Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByStatusOrderByQtdTokensDescByPage(Boolean status, Pageable pageable);	
	
	Optional<List<Produtos>> findAllByStatusOrderByPrecoReaisAscByPage(Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByStatusOrderByPrecoReaisDescByPage(Boolean status, Pageable pageable);	
	
	Optional<List<Produtos>> findAllByNomeAndStatusOrderByIdAscByPage(String nome, Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByNomeAndStatusOrderByNomeAscByPage(String nome, Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByNomeAndStatusOrderByNomeDescByPage(String nome, Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByNomeAndStatusOrderByQtdTokensAscByPage(String nome, Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByNomeAndStatusOrderByQtdTokensDescByPage(String nome, Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByNomeAndStatusOrderByPrecoReaisAscByPage(String nome, Boolean status, Pageable pageable);
	
	Optional<List<Produtos>> findAllByNomeAndStatusOrderByPrecoReaisDescByPage(String nome, Boolean status, Pageable pageable);	
	
	Optional<Produtos> findById(Long id);
	
	Optional<Produtos> findByNomeIgnoreCase(String nome);	
	
	Produtos save(Produtos produtos);
	
	Boolean deleteById(Long id);

}
