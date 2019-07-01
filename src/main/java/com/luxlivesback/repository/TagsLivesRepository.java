package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.TagsLives;

@Transactional(readOnly = true)
public interface TagsLivesRepository extends JpaRepository<TagsLives, Long> {
	
}
