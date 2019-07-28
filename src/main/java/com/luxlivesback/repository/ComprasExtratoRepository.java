package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.ComprasExtrato;

@Transactional(readOnly = true)
public interface ComprasExtratoRepository extends JpaRepository<ComprasExtrato, Long> {
	

	@Query("select c from ComprasExtrato c "
			+ "inner join fetch c.usuarios "
			+ "inner join fetch c.produtos "
			+ "inner join fetch c.tipoTransacao "
			+ "where c.id = ?1")
	ComprasExtrato findByIdLazyToEager(Long id);
	
	@Query("select c from ComprasExtrato c "
			+ "inner join fetch c.usuarios "
			+ "inner join fetch c.produtos "
			+ "inner join fetch c.tipoTransacao")
	List<ComprasExtrato> findAllLazyToEagerByPage(Pageable pageable);
	
	@Query("select c from ComprasExtrato c "
			+ "inner join fetch c.usuarios "
			+ "inner join fetch c.produtos "
			+ "inner join fetch c.tipoTransacao "
			+ "order by c.dataTransacao desc")
	List<ComprasExtrato> findAllLazyToEagerOrderByDataTransacaoDescByPage(Pageable pageable);
	
	@Query("select c from ComprasExtrato c "
			+ "inner join fetch c.usuarios "
			+ "inner join fetch c.produtos "
			+ "inner join fetch c.tipoTransacao "
			+ "where c.usuarios.id = ?1 order by c.dataTransacao desc")
	List<ComprasExtrato> findAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage(Long id, Pageable pageable);
	
	@Query("select c from ComprasExtrato c "
			+ "inner join fetch c.usuarios "
			+ "inner join fetch c.produtos "
			+ "inner join fetch c.tipoTransacao "
			+ "where c.usuarios.id = ?1 "
			+ "and c.tipoTransacao.id = ?2 order by c.dataTransacao desc")
	List<ComprasExtrato> findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long tt_id, Pageable pageable);
	
	@Query("select c from ComprasExtrato c "
			+ "inner join fetch c.usuarios "
			+ "inner join fetch c.produtos "
			+ "inner join fetch c.tipoTransacao "
			+ "where c.usuarios.id = ?1 "
			+ "and c.produtos.id = ?2 order by c.dataTransacao desc")
	List<ComprasExtrato> findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long p_id, Pageable pageable);
	
	@Query("select c from ComprasExtrato c "
			+ "inner join fetch c.usuarios "
			+ "inner join fetch c.produtos "
			+ "inner join fetch c.tipoTransacao "
			+ "where c.usuarios.id = ?1 "
			+ "and c.produtos.id = ?2 "
			+ "and c.tipoTransacao.id = ?3 order by c.dataTransacao desc")
	List<ComprasExtrato> findAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Long user_id, Long p_id, Long t_id, Pageable pageable);
}
