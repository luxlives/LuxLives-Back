package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.ComprasExtrato;
import com.luxlivesback.repository.ComprasExtratoRepository;
import com.luxlivesback.service.ComprasExtratoService;

@Service
public class ComprasExtratoServiceImpl implements ComprasExtratoService {
	
	private static final Logger log = LoggerFactory.getLogger(ComprasExtratoServiceImpl.class);
	
	@Autowired
	private ComprasExtratoRepository comprasExtratoRepository;

	@Override
	public Optional<List<ComprasExtrato>> findAll() {
		log.info("Buscando lista de ComprasExtrato");
		return Optional.ofNullable(comprasExtratoRepository.findAll());
	}

	@Override
	public Optional<List<ComprasExtrato>> findAllLazyToEagerByPage(Pageable pageable) {
		log.info("Buscando lista de ComprasExtrato LazyToEager, page: {}", pageable.toString());
		return Optional.ofNullable(comprasExtratoRepository.findAllLazyToEagerByPage(pageable));
	}

	@Override
	public Optional<List<ComprasExtrato>> findAllLazyToEagerOrderByDataTransacaoDescByPage(Pageable pageable) {
		log.info("Buscando lista de ComprasExtrato LazyToEager order by dataTransação desc, page: {}", pageable.toString());
		return Optional.ofNullable(comprasExtratoRepository.findAllLazyToEagerOrderByDataTransacaoDescByPage(pageable));
	}

	@Override
	public Optional<List<ComprasExtrato>> findAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage(Long id, Pageable pageable) {
		log.info("Buscando lista de ComprasExtrato LazyToEager order by dataTransação desc, usuario_id: {}, page: {}", id, pageable.toString());
		return Optional.ofNullable(comprasExtratoRepository.findAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage(id , pageable));
	}

	@Override
	public Optional<List<ComprasExtrato>> findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long tt_id, Pageable pageable) {
		log.info("Buscando lista de ComprasExtrato LazyToEager order by dataTransação desc, usuario_id: {}, tipoTransação_id: {}, page: {}", user_id, tt_id, pageable.toString());
		return Optional.ofNullable(comprasExtratoRepository.findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(user_id , tt_id, pageable));
	}

	@Override
	public Optional<List<ComprasExtrato>> findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long p_id, Pageable pageable) {
		log.info("Buscando lista de ComprasExtrato LazyToEager order by dataTransação desc, usuario_id: {}, produto_id: {}, page: {}", user_id, p_id, pageable.toString());
		return Optional.ofNullable(comprasExtratoRepository.findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(user_id , p_id, pageable));
	}

	@Override
	public Optional<List<ComprasExtrato>> findAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long p_id, Long tt_id, Pageable pageable) {
		log.info("Buscando lista de ComprasExtrato LazyToEager order by dataTransação desc, usuario_id: {}, produto_id: {}, tipoTransação_id: {}, page: {}", user_id, p_id, tt_id, pageable.toString());
		return Optional.ofNullable(comprasExtratoRepository.findAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(user_id , p_id, tt_id, pageable));
	}

	@Override
	public Optional<ComprasExtrato> findByIdLazyToEager(Long id) {
		log.info("Buscando ComprasExtrato LazyToEager pelo id: {}", id);
		return Optional.ofNullable(comprasExtratoRepository.findByIdLazyToEager(id));
	}

	@Override
	public Optional<ComprasExtrato> findById(Long id) {
		log.info("Buscando ComprasExtrato pelo id: {}", id);
		return comprasExtratoRepository.findById(id);
	}

	@Override
	public ComprasExtrato save(ComprasExtrato comprasExtrato) {
		log.info("Persistindo ComprasExtrato: {}", comprasExtrato.toString());
		return comprasExtratoRepository.save(comprasExtrato);
	}

	@Override
	public Boolean deleteById(Long id) {
		log.info("Deletando ComprasExtrato pelo id: {}", id);
		try {
			comprasExtratoRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
