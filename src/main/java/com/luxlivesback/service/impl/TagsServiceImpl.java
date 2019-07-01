package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.Tags;
import com.luxlivesback.repository.TagsRepository;
import com.luxlivesback.service.TagsService;

@Service
public class TagsServiceImpl implements TagsService {
	
	private static final Logger log = LoggerFactory.getLogger(TagsServiceImpl.class);
	
	@Autowired
	private TagsRepository tagsRepository;

	@Override
	public Optional<List<Tags>> findAll() {
		log.info("Buscando uma lista de Tags");
		return Optional.ofNullable(tagsRepository.findAll());
	}

	@Override
	public Optional<List<Tags>> findAllByOrderByNome() {
		log.info("Buscando uma lista de Tags em ordem alfabética");
		return Optional.ofNullable(tagsRepository.findAllByOrderByNome());
	}

	@Override
	public Optional<List<Tags>> findAllByNomeStartingWithIgnoreCaseOrderByNome(String nome) {
		log.info("Buscando uma lista de Tags em ordem alfabética relacionado nome: {}", nome);
		return Optional.ofNullable(tagsRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome(nome));
	}

	@Override
	public Optional<Tags> findById(Long id) {
		log.info("Buscando uma Tag pelo id: {}", id);
		return tagsRepository.findById(id);
	}

	@Override
	public Optional<Tags> findByNomeIgnoreCase(String nome) {
		log.info("Buscando uma Tag pelo nome: {}", nome);
		return Optional.ofNullable(tagsRepository.findByNomeIgnoreCase(nome));
	}

	@Override
	public Tags save(Tags tags) {
		log.info("Persistindo a Tag: {}", tags.toString());
		return tagsRepository.save(tags);
	}

	@Override
	public Boolean deleteById(Long id) {
		log.info("Deletando a Tag de id: {}", id);
		try {
			tagsRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}	

}
