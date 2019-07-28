package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.luxlivesback.model.ComprasExtrato;

public interface ComprasExtratoService {
	
	Optional<List<ComprasExtrato>> findAll();
	
	Optional<List<ComprasExtrato>> findAllLazyToEagerByPage(Pageable pageable);
	
	Optional<List<ComprasExtrato>> findAllLazyToEagerOrderByDataTransacaoDescByPage(Pageable pageable);
	
	Optional<List<ComprasExtrato>> findAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage(Long id, Pageable pageable);
	
	Optional<List<ComprasExtrato>> findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long tt_id, Pageable pageable);
	
	Optional<List<ComprasExtrato>> findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long p_id, Pageable pageable);
	
	Optional<List<ComprasExtrato>> findAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long p_id, Long tt_id, Pageable pageable);	
	
	Optional<ComprasExtrato> findByIdLazyToEager(Long id);
	
	Optional<ComprasExtrato> findById(Long id);
	
	ComprasExtrato save(ComprasExtrato comprasExtrato);
	
	Boolean deleteById(Long id);

}
