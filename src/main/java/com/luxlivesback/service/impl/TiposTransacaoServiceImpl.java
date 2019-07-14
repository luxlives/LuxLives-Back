package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.TiposTransacao;
import com.luxlivesback.repository.TiposTransacaoRepository;
import com.luxlivesback.service.TiposTransacaoService;

@Service
public class TiposTransacaoServiceImpl implements TiposTransacaoService {

	private static final Logger log = LoggerFactory.getLogger(TiposTransacaoServiceImpl.class);
	
	@Autowired
	private TiposTransacaoRepository tiposTransacaoRepository;

	@Override
	public Optional<List<TiposTransacao>> findAll() {
		log.info("Buscando lista de TiposTransacao");
		return Optional.ofNullable(tiposTransacaoRepository.findAll());
	}

	@Override
	public Optional<List<TiposTransacao>> findAllByOrderByNome() {
		log.info("Buscando lista de TiposTransacao em ordem alfabética");
		return Optional.ofNullable(tiposTransacaoRepository.findAllByOrderByNome());
	}

	@Override
	public Optional<TiposTransacao> findById(Long id) {
		log.info("Buscando TiposTransacao pelo id: {}", id);
		return tiposTransacaoRepository.findById(id);
	}

	@Override
	public Optional<TiposTransacao> findByNomeIgnoreCase(String nome) {
		log.info("Buscando TiposTransacao pelo nome: {}", nome);
		return Optional.ofNullable(tiposTransacaoRepository.findByNomeIgnoreCase(nome));
	}

	@Override
	public TiposTransacao save(TiposTransacao tiposTransacao) {
		log.info("Persistindo TiposTransacao: {}", tiposTransacao.toString());
		return tiposTransacaoRepository.save(tiposTransacao);
	}

	@Override
	public Boolean deleteById(Long id) {
		log.info("Deletando TiposTransacao pelo id: {}", id);
		try {
			tiposTransacaoRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
