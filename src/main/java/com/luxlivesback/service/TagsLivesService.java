package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.luxlivesback.model.TagsLives;

public interface TagsLivesService {
	
	Optional<List<TagsLives>> findAllLazyToEagerOrderByLives();	
	
	Optional<List<TagsLives>> findAllLazyToEagerOrderByTags();
	
	Optional<List<TagsLives>> findAllByLivesIdLazyToEagerOrderByTags(Long liveId);	
	
	Optional<List<TagsLives>> findAllByTagsIdLazyToEagerOrderByLives(Long tagId);	
	
	Optional<List<TagsLives>> findAllLazyToEagerOrderByLivesByPage(Pageable pageable);
	
	Optional<List<TagsLives>> findAllLazyToEagerOrderByTagsByPage(Pageable pageable);	
	
	Optional<List<TagsLives>> findAllByLivesIdLazyToEagerOrderByTagsByPage(Long liveId, Pageable pageable);	
	
	Optional<List<TagsLives>> findAllByTagsIdLazyToEagerOrderByLivesByPage(Long tagId, Pageable pageable);	
	
	Optional<TagsLives> findByLivesIdAndTagsIdLazyToEager(Long liveId, Long tagId);	
	
	TagsLives save(TagsLives tagsLives);
	
	Boolean deleteByLivesIdAndTagsId(Long liveId, Long tagId);
	
	Boolean deleteByLivesId(Long liveId);
	
	Boolean deleteByTagsId(Long tagId);	
}
