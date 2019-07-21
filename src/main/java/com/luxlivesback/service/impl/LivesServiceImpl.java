package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.Lives;
import com.luxlivesback.repository.LivesRepository;
import com.luxlivesback.service.LivesService;

@Service
public class LivesServiceImpl implements LivesService {
	
	private static final Logger log = LoggerFactory.getLogger(LivesServiceImpl.class);
	
	@Autowired
	private LivesRepository livesRepository;

	@Override
	public Optional<List<Lives>> findAll() {
		log.info("Buscando lista de Lives");
		return Optional.ofNullable(livesRepository.findAll());
	}

	@Override
	public Optional<List<Lives>> findAllLazyToEagerByPage(Pageable pageable) {
		log.info("Buscando lista de Lives LazyToEager page: {}", pageable.toString());
		return Optional.ofNullable(livesRepository.findAllLazyToEagerByPage(pageable));
	}
	
	@Override
	public Optional<List<Lives>> findAllLazyToEagerOrderByIdDescByPage(Pageable pageable) {
		log.info("Buscando lista de Lives LazyToEager por ordem de id desc page: {}", pageable.toString());
		return Optional.ofNullable(livesRepository.findAllLazyToEagerOrderByIdDescByPage(pageable));
	}

	@Override
	public Optional<List<Lives>> findAllLazyToEagerOrderByTituloByPage(Pageable pageable) {
		log.info("Buscando lista de Lives LazyToEager por ordem de título page: {}", pageable.toString());
		return Optional.ofNullable(livesRepository.findAllLazyToEagerOrderByTituloByPage(pageable));
	}

	@Override
	public Optional<List<Lives>> findAllLazyToEagerOrderByGeneroByPage(Pageable pageable) {
		log.info("Buscando lista de Lives LazyToEager por ordem de genero page: {}", pageable.toString());
		return Optional.ofNullable(livesRepository.findAllLazyToEagerOrderByGeneroByPage(pageable));
	}

	@Override
	public Optional<List<Lives>> findAllLazyToEagerOrderByUsuariosByPage(Pageable pageable) {
		log.info("Buscando lista de Lives LazyToEager por ordem de usuarios page: {}", pageable.toString());
		return Optional.ofNullable(livesRepository.findAllLazyToEagerOrderByUsuariosByPage(pageable));
	}

	@Override
	public Optional<List<Lives>> findAllLazyToEagerOrderByDataIniDescByPage(Pageable pageable) {
		log.info("Buscando lista de Lives LazyToEager por ordem de dataini desc page: {}", pageable.toString());
		return Optional.ofNullable(livesRepository.findAllLazyToEagerOrderByDataIniDescByPage(pageable));
	}
	
	@Override
	public Optional<List<Lives>> findAllByTituloLazyToEagerOrderByIdDescByPage(String titulo, Pageable pageable) {
		log.info("Buscando lista de Lives LazToEager por ordem de id, titulo: {}, page: {}", titulo, pageable.toString());
		return Optional.ofNullable(livesRepository.findAllByTituloLazyToEagerOrderByIdDescByPage(titulo, pageable));
	}	

	@Override
	public Optional<List<Lives>> findAllByGeneroLazyToEagerOrderByIdDescByPage(String genero, Pageable pageable) {
		log.info("Buscando lista de Lives LazToEager por ordem de id, genero: {}, page: {}", genero, pageable.toString());
		return Optional.ofNullable(livesRepository.findAllByGeneroLazyToEagerOrderByIdDescByPage(genero, pageable));
	}

	@Override
	public Optional<List<Lives>> findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(Long id, Pageable pageable) {
		log.info("Buscando lista de Lives LazToEager por ordem de id, usuario_id: {}, page: {}", id, pageable.toString());
		return Optional.ofNullable(livesRepository.findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(id, pageable));
	}	

	@Override
	public Optional<Lives> findById(Long id) {
		log.info("Buscando Live pela id: {}", id);
		return livesRepository.findById(id);
	}

	@Override
	public Optional<Lives> findByIdLazyToEager(Long id) {
		log.info("Buscando Lives LazyToEager pela id: {}", id);
		return Optional.ofNullable(livesRepository.findByIdLazyToEager(id));
	}

	@Override
	public Lives save(Lives lives) {
		log.info("Persistindo live: {}", lives.toString());
		return livesRepository.save(lives);
	}

	@Override
	public Boolean deleteById(Long id) {
		log.info("Deletando Live pelo id: {}", id);
		try {
			livesRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}	

}
