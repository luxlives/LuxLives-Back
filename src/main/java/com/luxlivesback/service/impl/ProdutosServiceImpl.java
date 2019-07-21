package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.Produtos;
import com.luxlivesback.repository.ProdutosRepository;
import com.luxlivesback.service.ProdutosService;

@Service
public class ProdutosServiceImpl implements ProdutosService {
	
	private static final Logger log = LoggerFactory.getLogger(ProdutosServiceImpl.class);
	
	@Autowired
	private ProdutosRepository produtosRepository;

	@Override
	public Optional<List<Produtos>> findAll() {
		log.info("Buscando lista de Produtos");
		return Optional.ofNullable(produtosRepository.findAll());
	}
	
	@Override
	public Optional<List<Produtos>> findAllByStatus(Boolean status) {
		log.info("Buscando lista de Produtos pelo status: {}", status);
		return Optional.ofNullable(produtosRepository.findAllByStatus(status));
	}
	
	@Override
	public Optional<List<Produtos>> findAllByStatusOrderByIdAscByPage(Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos order by id pelo status: {}, Pageable{}", status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByStatusOrderByIdAscByPage(status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByStatusOrderByNomeAscByPage(Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos order by nome pelo status: {}, Pageable{}", status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByStatusOrderByNomeAscByPage(status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByStatusOrderByNomeDescByPage(Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos order by nome desc pelo status: {}, Pageable{}", status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByStatusOrderByNomeDescByPage(status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByStatusOrderByQtdTokensAscByPage(Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos order by token pelo status: {}, Pageable{}", status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByStatusOrderByQtdTokensAscByPage(status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByStatusOrderByQtdTokensDescByPage(Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos order by token desc pelo status: {}, Pageable{}", status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByStatusOrderByQtdTokensDescByPage(status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByStatusOrderByPrecoReaisAscByPage(Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos order by preço pelo status: {}, Pageable{}", status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByStatusOrderByPrecoReaisAscByPage(status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByStatusOrderByPrecoReaisDescByPage(Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos order by preço desc pelo status: {}, Pageable{}", status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByStatusOrderByPrecoReaisDescByPage(status, pageable));
	}
	
	@Override
	public Optional<List<Produtos>> findAllByNomeAndStatusOrderByIdAscByPage(String nome, Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos pelo nome: {}, order by id pelo status: {}, Pageable{}", nome, status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByNomeAndStatusOrderByIdAscByPage(nome ,status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByNomeAndStatusOrderByNomeAscByPage(String nome, Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos pelo nome: {}, order by nome pelo status: {}, Pageable{}", nome, status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByNomeAndStatusOrderByNomeAscByPage(nome ,status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByNomeAndStatusOrderByNomeDescByPage(String nome, Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos pelo nome: {}, order by nome desc pelo status: {}, Pageable{}", nome, status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByNomeAndStatusOrderByNomeDescByPage(nome ,status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByNomeAndStatusOrderByQtdTokensAscByPage(String nome, Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos pelo nome: {}, order by qtdTokens pelo status: {}, Pageable{}", nome, status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByNomeAndStatusOrderByQtdTokensAscByPage(nome ,status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByNomeAndStatusOrderByQtdTokensDescByPage(String nome, Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos pelo nome: {}, order by qtdTokens desc pelo status: {}, Pageable{}", nome, status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByNomeAndStatusOrderByQtdTokensDescByPage(nome ,status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByNomeAndStatusOrderByPrecoReaisAscByPage(String nome, Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos pelo nome: {}, order by precoReais pelo status: {}, Pageable{}", nome, status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisAscByPage(nome ,status, pageable));
	}

	@Override
	public Optional<List<Produtos>> findAllByNomeAndStatusOrderByPrecoReaisDescByPage(String nome, Boolean status, Pageable pageable) {
		log.info("Buscando lista de Produtos pelo nome: {}, order by precoReais desc pelo status: {}, Pageable{}", nome, status, pageable.toString());
		return Optional.ofNullable(produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisDescByPage(nome ,status, pageable));
	}	

	@Override
	public Optional<Produtos> findById(Long id) {
		log.info("Buscando Produto pelo id: {}", id);
		return produtosRepository.findById(id);
	}
	
	@Override
	public Optional<Produtos> findByNomeIgnoreCase(String nome) {
		log.info("Buscando Produto pelo nome: {}", nome);
		return Optional.ofNullable(produtosRepository.findByNomeIgnoreCase(nome));
	}

	@Override
	public Produtos save(Produtos produtos) {
		log.info("Persistindo Produtos: {}", produtos.toString());
		return produtosRepository.save(produtos);
	}	

	@Override
	public Boolean deleteById(Long id) {
		log.info("Deletando Produto pelo id: {}", id);
		try {
			produtosRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}	

}
