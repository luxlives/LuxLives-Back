package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Produtos;

@Transactional(readOnly = true)
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
	
	List<Produtos> findAllByStatus(Boolean status);
	
	@Query("select c from Produtos c where c.status = ?1 order by c.id")
	List<Produtos> findAllByStatusOrderByIdAscByPage(Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.status = ?1 order by c.nome asc")
	List<Produtos> findAllByStatusOrderByNomeAscByPage(Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.status = ?1 order by c.nome desc")
	List<Produtos> findAllByStatusOrderByNomeDescByPage(Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.status = ?1 order by c.qtdTokens asc")
	List<Produtos> findAllByStatusOrderByQtdTokensAscByPage(Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.status = ?1 order by c.qtdTokens desc")
	List<Produtos> findAllByStatusOrderByQtdTokensDescByPage(Boolean status, Pageable pageable);	
	
	@Query("select c from Produtos c where c.status = ?1 order by c.precoReais asc")
	List<Produtos> findAllByStatusOrderByPrecoReaisAscByPage(Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.status = ?1 order by c.precoReais desc")
	List<Produtos> findAllByStatusOrderByPrecoReaisDescByPage(Boolean status, Pageable pageable);	
	
	@Query("select c from Produtos c where c.nome like %?1% and c.status = ?2 order by c.id")
	List<Produtos> findAllByNomeAndStatusOrderByIdAscByPage(String nome, Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.nome like %?1% and c.status = ?2 order by c.nome asc")
	List<Produtos> findAllByNomeAndStatusOrderByNomeAscByPage(String nome, Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.nome like %?1% and c.status = ?2 order by c.nome desc")
	List<Produtos> findAllByNomeAndStatusOrderByNomeDescByPage(String nome, Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.nome like %?1% and c.status = ?2 order by c.qtdTokens asc")
	List<Produtos> findAllByNomeAndStatusOrderByQtdTokensAscByPage(String nome, Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.nome like %?1% and c.status = ?2 order by c.qtdTokens desc")
	List<Produtos> findAllByNomeAndStatusOrderByQtdTokensDescByPage(String nome, Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.nome like %?1% and c.status = ?2 order by c.precoReais asc")
	List<Produtos> findAllByNomeAndStatusOrderByPrecoReaisAscByPage(String nome, Boolean status, Pageable pageable);
	
	@Query("select c from Produtos c where c.nome like %?1% and c.status = ?2 order by c.precoReais desc")
	List<Produtos> findAllByNomeAndStatusOrderByPrecoReaisDescByPage(String nome, Boolean status, Pageable pageable);	
	
	Produtos findByNomeIgnoreCase(String nome);	
	
}
