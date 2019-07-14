package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Lives;

@Transactional(readOnly = true)
public interface LivesRepository extends JpaRepository<Lives, Long> {
	
	@Query("select c from Lives c inner join fetch c.usuarios order by c.id")
	List<Lives> findAllLazyToEagerByPage(Pageable pageable);
	
	@Query("select c from Lives c inner join fetch c.usuarios order by c.id desc")
	List<Lives> findAllLazyToEagerOrderByIdDescByPage(Pageable pageable);
	
	@Query("select c from Lives c inner join fetch c.usuarios order by c.titulo")
	List<Lives> findAllLazyToEagerOrderByTituloByPage(Pageable pageable);
	
	@Query("select c from Lives c inner join fetch c.usuarios order by c.genero")
	List<Lives> findAllLazyToEagerOrderByGeneroByPage(Pageable pageable);
	
	@Query("select c from Lives c inner join fetch c.usuarios order by c.usuarios")
	List<Lives> findAllLazyToEagerOrderByUsuariosByPage(Pageable pageable);
	
	@Query("select c from Lives c inner join fetch c.usuarios order by c.dataIni desc")
	List<Lives> findAllLazyToEagerOrderByDataIniDescByPage(Pageable pageable);	
	
	@Query("select c from Lives c inner join fetch c.usuarios where c.titulo like %?1% order by c.id desc")
	List<Lives> findAllByTituloLazyToEagerOrderByIdDescByPage(String titulo, Pageable pageable);
	
	@Query("select c from Lives c inner join fetch c.usuarios where c.genero like %?1% order by c.id desc")
	List<Lives> findAllByGeneroLazyToEagerOrderByIdDescByPage(String genero, Pageable pageable);	
	
	@Query("select c from Lives c inner join fetch c.usuarios where c.usuarios.id = ?1 order by c.id desc")
	List<Lives> findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(Long id, Pageable pageable);	
	
	@Query("select c from Lives c inner join fetch c.usuarios where c.id = ?1")
	Lives findByIdLazyToEager(Long id);
	
}
