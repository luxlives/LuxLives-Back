package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.TagsLives;

@Transactional(readOnly = true)
public interface TagsLivesRepository extends JpaRepository<TagsLives, Long> {
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags order by c.lives")
	List<TagsLives> findAllLazyToEagerOrderByLives();
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags order by c.tags")
	List<TagsLives> findAllLazyToEagerOrderByTags();	
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags where c.lives.id = ?1 order by c.tags")
	List<TagsLives> findAllByLivesIdLazyToEagerOrderByTags(Long liveId);	
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags where c.tags.id = ?1 order by c.lives")
	List<TagsLives> findAllByTagsIdLazyToEagerOrderByLives(Long tagId);	
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags order by c.lives")
	List<TagsLives> findAllLazyToEagerOrderByLivesByPage(Pageable pageable);
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags order by c.tags")
	List<TagsLives> findAllLazyToEagerOrderByTagsByPage(Pageable pageable);	
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags where c.lives.id = ?1 order by c.tags")
	List<TagsLives> findAllByLivesIdLazyToEagerOrderByTagsByPage(Long liveId, Pageable pageable);	
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags where c.tags.id = ?1 order by c.lives")
	List<TagsLives> findAllByTagsIdLazyToEagerOrderByLivesByPage(Long tagId, Pageable pageable);	
	
	@Query("select c from TagsLives c inner join fetch c.lives inner join fetch c.tags where c.lives.id = ?1 and c.tags.id = ?2")
	TagsLives findByLivesIdAndTagsIdLazyToEager(Long liveId, Long tagId);
	
	@Modifying
	@Transactional(readOnly = false)
	@Query("delete from TagsLives c where c.lives.id = ?1 and c.tags.id = ?2")
	void deleteByLivesIdAndTagsId(Long liveId, Long tagId);
	
	@Modifying
	@Transactional(readOnly = false)
	@Query("delete from TagsLives c where c.lives.id = ?1")
	void deleteByLivesId(Long liveId);
	
	@Modifying
	@Transactional(readOnly = false)
	@Query("delete from TagsLives c where c.tags.id = ?1")
	void deleteByTagsId(Long tagId);	
	
}
