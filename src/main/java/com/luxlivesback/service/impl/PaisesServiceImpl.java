package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.Paises;
import com.luxlivesback.repository.PaisesRepository;
import com.luxlivesback.service.PaisesService;

@Service
public class PaisesServiceImpl implements PaisesService {
	
	private static final Logger log = LoggerFactory.getLogger(PaisesServiceImpl.class);
	
	@Autowired
	private PaisesRepository paisesRepository;

	@Override
	public Optional<List<Paises>> findAll() {
		log.info("Buscando lista de paises");
		return Optional.ofNullable(paisesRepository.findAll());
	}

	@Override
	public Optional<List<Paises>> findAllByOrderByNome() {
		log.info("Buscando lista de paises em order alfabética");
		return Optional.ofNullable(paisesRepository.findAllByOrderByNome());
	}

	@Override
	public Optional<Paises> findById(Long id) {
		log.info("Buscando pais pelo id: {}", id);
		return paisesRepository.findById(id);
	}

	@Override
	public Optional<Paises> findByNomeIgnoreCase(String nome) {
		log.info("Buscando pais pelo nome: {}", nome);
		return Optional.ofNullable(paisesRepository.findByNomeIgnoreCase(nome));
	}

	@Override
	public Paises save(Paises paises) {
		log.info("Persistindo pais: {}", paises.toString());
		return paisesRepository.save(paises);
	}

	@Override
	public Boolean deleteById(Long id) {
		log.info("Deletando pais pelo id: {}", id);
		try {
			paisesRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
