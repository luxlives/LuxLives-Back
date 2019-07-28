package com.luxlivesback.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.TagsLives;
import com.luxlivesback.repository.TagsLivesRepository;
import com.luxlivesback.service.TagsLivesService;

@Service
public class TagsLivesServiceImpl implements TagsLivesService {
	
	private static final Logger log = LoggerFactory.getLogger(TagsLivesServiceImpl.class);
	
	@Autowired
	private TagsLivesRepository tagsLivesRepository;

	@Override
	public Optional<List<TagsLives>> findAllLazyToEagerOrderByLives() {
		log.info("Buscando lista de TagsLives LazyToEager OrderByLives");
		return Optional.ofNullable(tagsLivesRepository.findAllLazyToEagerOrderByLives());
	}	
	
	@Override
	public Optional<List<TagsLives>> findAllLazyToEagerOrderByTags() {
		log.info("Buscando lista de TagsLives LazyToEager OrderByTags");
		return Optional.ofNullable(tagsLivesRepository.findAllLazyToEagerOrderByTags());
	}

	@Override
	public Optional<List<TagsLives>> findAllByLivesIdLazyToEagerOrderByTags(Long liveId) {
		log.info("Buscando lista de TagsLives LazyToEager OrderByTags, live_id: {}", liveId);
		return Optional.ofNullable(tagsLivesRepository.findAllByLivesIdLazyToEagerOrderByTags(liveId));
	}

	@Override
	public Optional<List<TagsLives>> findAllByTagsIdLazyToEagerOrderByLives(Long tagId) {
		log.info("Buscando lista de TagsLives LazyToEager OrderByLives, tag_id: {}", tagId);
		return Optional.ofNullable(tagsLivesRepository.findAllByTagsIdLazyToEagerOrderByLives(tagId));
	}	

	@Override
	public Optional<List<TagsLives>> findAllLazyToEagerOrderByLivesByPage(Pageable pageable) {
		log.info("Buscando lista de TagsLives LazyToEager OrderByLives, pag: {}", pageable.toString());
		return Optional.ofNullable(tagsLivesRepository.findAllLazyToEagerOrderByLivesByPage(pageable));
	}

	@Override
	public Optional<List<TagsLives>> findAllLazyToEagerOrderByTagsByPage(Pageable pageable) {
		log.info("Buscando lista de TagsLives LazyToEager OrderByTags, pag: {}", pageable.toString());
		return Optional.ofNullable(tagsLivesRepository.findAllLazyToEagerOrderByTagsByPage(pageable));
	}	

	@Override
	public Optional<List<TagsLives>> findAllByLivesIdLazyToEagerOrderByTagsByPage(Long liveId, Pageable pageable) {
		log.info("Buscando lista de TagsLives LazyToEager OrderByTags, liveId: {}, pag: {}", liveId, pageable.toString());
		return Optional.ofNullable(tagsLivesRepository.findAllByLivesIdLazyToEagerOrderByTagsByPage(liveId, pageable));
	}

	@Override
	public Optional<List<TagsLives>> findAllByTagsIdLazyToEagerOrderByLivesByPage(Long tagId, Pageable pageable) {
		log.info("Buscando lista de TagsLives LazyToEager OrderByLives, tagId: {}, pag: {}", tagId, pageable.toString());
		return Optional.ofNullable(tagsLivesRepository.findAllByTagsIdLazyToEagerOrderByLivesByPage(tagId, pageable));
	}	

	@Override
	public Optional<TagsLives> findByLivesIdAndTagsIdLazyToEager(Long liveId, Long tagId) {
		log.info("Buscando TagsLives LazyToEager pela live_id: {}, tag_id: {}", liveId, tagId);
		return Optional.ofNullable(tagsLivesRepository.findByLivesIdAndTagsIdLazyToEager(liveId, tagId));
	}

	@Override
	public TagsLives save(TagsLives tagsLives) {
		log.info("Persistindo TagsLives: {}", tagsLives.toString());
		return tagsLivesRepository.save(tagsLives);
	}

	@Override
	public Boolean deleteByLivesIdAndTagsId(Long liveId, Long tagId) {
		log.info("Deletando TagsLives pela live_id: {}, tag_id: {}", liveId, tagId);
		try {
			tagsLivesRepository.deleteByLivesIdAndTagsId(liveId, tagId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteByLivesId(Long liveId) {
		log.info("Deletando lista de TagsLives pela live_id: {}", liveId);
		try {
			tagsLivesRepository.deleteByLivesId(liveId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteByTagsId(Long tagId) {
		log.info("Deletando TagsLives pela tag_id: {}", tagId);
		try {
			tagsLivesRepository.deleteByTagsId(tagId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}	

}
