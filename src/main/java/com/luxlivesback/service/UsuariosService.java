package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luxlivesback.model.Usuarios;

public interface UsuariosService {
	
	Optional<List<Usuarios>> findAll();
	
	Optional<List<Usuarios>> findAllByPage(Pageable pageable);
	
	Optional<List<Usuarios>> findAllByNomeIgnoreCase(String nome);
	
	Optional<Page<Usuarios>> findAllByNomeStartingWithIgnoreCaseOrderByNome(String nome, Pageable pageable);
	
	Optional<Usuarios> findById(Long id);
	
	Optional<Usuarios> findByEmail(String email);
	
	Optional<Usuarios> findByLogin(String login);
	
	Optional<Usuarios> findByCpf(String cpf);
	
	Usuarios save(Usuarios usuarios);
	
	Boolean deleteById(Long id);
}
