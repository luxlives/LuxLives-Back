package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.Idiomas;
import com.luxlivesback.repository.IdiomasRepository;
import com.luxlivesback.service.IdiomasService;

@Service
public class IdiomasServiceImpl implements IdiomasService {
	
	private static final Logger log = LoggerFactory.getLogger(IdiomasServiceImpl.class);
	
	@Autowired
	private IdiomasRepository idiomasRepository;

	@Override
	public Optional<List<Idiomas>> findAll() {
		log.info("Buscando lista de idiomas");
		return Optional.ofNullable(idiomasRepository.findAll());
	}

	@Override
	public Optional<List<Idiomas>> findAllByOrderByLingua() {
		log.info("Buscando lista de idiomas em ordem alfabetica");
		return Optional.ofNullable(idiomasRepository.findAllByOrderByLingua());
	}

	@Override
	public Optional<Idiomas> findById(Long id) {
		log.info("Buscando idioma pelo id: {}", id);
		return idiomasRepository.findById(id);
	}

	@Override
	public Optional<Idiomas> findByLinguaIgnoreCase(String lingua) {
		log.info("Buscando idioma pela lingua: {}", lingua);
		return Optional.ofNullable(idiomasRepository.findByLinguaIgnoreCase(lingua));
	}

	@Override
	public Idiomas save(Idiomas idiomas) {
		log.info("Persistindo idioma: {}", idiomas.toString());
		return idiomasRepository.save(idiomas);
	}

	@Override
	public Boolean deleteById(Long id) {
		log.info("Deletando idioma pelo id: {}", id);
		try {
			idiomasRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
