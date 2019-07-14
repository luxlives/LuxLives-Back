package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Usuarios;

@Transactional(readOnly = true)
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {	
	
	@Query("select c from Usuarios c order by c.nome")
	List<Usuarios> findAllByPage(Pageable pageable);
	
	List<Usuarios> findAllByNomeStartingWithIgnoreCaseOrderByNome(String nome);
	
	Page<Usuarios> findAllByNomeStartingWithIgnoreCaseOrderByNome(String nome, Pageable pageable);
	
	Usuarios findByEmail(String email);
	
	Usuarios findByLogin(String login);
	
	Usuarios findByCpf(String cpf);
	
}
