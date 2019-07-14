package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.Usuarios;
import com.luxlivesback.repository.UsuariosRepository;
import com.luxlivesback.service.UsuariosService;

@Service
public class UsuariosServiceImpl implements UsuariosService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuariosServiceImpl.class);	
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Override
	public Optional<List<Usuarios>> findAll() {
		log.info("Buscando lista de usuarios");
		return Optional.ofNullable(usuariosRepository.findAll());
	}

	@Override
	public Optional<List<Usuarios>> findAllByPage(Pageable pageable) {
		log.info("Buscando lista de usuarios paginados");
		return Optional.ofNullable(usuariosRepository.findAllByPage(pageable));
	}
	
	@Override
	public Optional<List<Usuarios>> findAllByNomeIgnoreCase(String nome) {
		log.info("Buscando lista de usuarios paginados pelo nome: {}", nome);
		return Optional.ofNullable(usuariosRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome(nome));
	}	
	
	@Override
	public Optional<Page<Usuarios>> findAllByNomeStartingWithIgnoreCaseOrderByNome(String nome, Pageable pageable) {
		log.info("Buscando lista de usuarios paginados pelo nome: {}, pag: {}", nome, pageable.toString());
		return Optional.ofNullable(usuariosRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome(nome, pageable));
	}

	@Override
	public Optional<Usuarios> findById(Long id) {
		log.info("Buscando usuário pelo id: {}", id);
		return usuariosRepository.findById(id);
	}

	@Override
	public Optional<Usuarios> findByEmail(String email) {
		log.info("Buscando usuário pelo email: {}", email);
		return Optional.ofNullable(usuariosRepository.findByEmail(email));
	}

	@Override
	public Optional<Usuarios> findByLogin(String login) {
		log.info("Buscando usuário pelo login: {}", login);
		return Optional.ofNullable(usuariosRepository.findByLogin(login));
	}

	@Override
	public Optional<Usuarios> findByCpf(String cpf) {
		log.info("Buscando usuário pelo cpf: {}", cpf);
		return Optional.ofNullable(usuariosRepository.findByCpf(cpf));
	}

	@Override
	public Usuarios save(Usuarios usuarios) {
		log.info("Persistindo usuário: {}", usuarios.toString());
		return usuariosRepository.save(usuarios);
	}

	@Override
	public Boolean deleteById(Long id) {
		try {
			log.info("Deletando usuário pelo id: {}", id);
			usuariosRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}		
	}	
}
