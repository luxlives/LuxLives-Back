package com.luxlivesback.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxlivesback.model.TagsLives;
import com.luxlivesback.repository.TagsLivesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TagsLivesServiceTest {
	
	@MockBean
	private TagsLivesRepository tagsLivesRepository;
	
	@Autowired
	private TagsLivesService tagsLivesService;
	
	private static final Long LIVE_ID = Long.valueOf(1);
	private static final Long TAG_ID = Long.valueOf(1);
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.tagsLivesRepository.findAllLazyToEagerOrderByLives()).willReturn(new ArrayList<TagsLives>());
		BDDMockito.given(this.tagsLivesRepository.findAllLazyToEagerOrderByTags()).willReturn(new ArrayList<TagsLives>());
		BDDMockito.given(this.tagsLivesRepository.findAllByLivesIdLazyToEagerOrderByTags(Mockito.anyLong())).willReturn(new ArrayList<TagsLives>());
		BDDMockito.given(this.tagsLivesRepository.findAllByTagsIdLazyToEagerOrderByLives(Mockito.anyLong())).willReturn(new ArrayList<TagsLives>());		
		BDDMockito.given(this.tagsLivesRepository.findAllLazyToEagerOrderByLivesByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<TagsLives>());
		BDDMockito.given(this.tagsLivesRepository.findAllLazyToEagerOrderByTagsByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<TagsLives>());		
		BDDMockito.given(this.tagsLivesRepository.findAllByLivesIdLazyToEagerOrderByTagsByPage(Mockito.anyLong(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<TagsLives>());
		BDDMockito.given(this.tagsLivesRepository.findAllByTagsIdLazyToEagerOrderByLivesByPage(Mockito.anyLong(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<TagsLives>());
		BDDMockito.given(this.tagsLivesRepository.findByLivesIdAndTagsIdLazyToEager(Mockito.anyLong(), Mockito.anyLong())).willReturn(new TagsLives());		
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByLives() {
		List<TagsLives> tagsLives = tagsLivesService.findAllLazyToEagerOrderByLives().get();
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByTags() {
		List<TagsLives> tagsLives = tagsLivesService.findAllLazyToEagerOrderByTags().get();
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testFindAllByLivesIdLazyToEagerOrderByTags() {
		List<TagsLives> tagsLives = tagsLivesService.findAllByLivesIdLazyToEagerOrderByTags(LIVE_ID).get();
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testFindAllByTagsIdLazyToEagerOrderByLives() {
		List<TagsLives> tagsLives = tagsLivesService.findAllByTagsIdLazyToEagerOrderByLives(TAG_ID).get();
		assertNotNull(tagsLives);
	}	
	
	@Test
	public void testFindAllLazyToEagerOrderByLivesByPage() {
		List<TagsLives> tagsLives = tagsLivesService.findAllLazyToEagerOrderByLivesByPage(PageRequest.of(0, 5)).get();
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByTagsByPage() {
		List<TagsLives> tagsLives = tagsLivesService.findAllLazyToEagerOrderByTagsByPage(PageRequest.of(0, 5)).get();
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testFindAllByLivesIdLazyToEagerOrderByTagsByPage() {
		List<TagsLives> tagsLives = tagsLivesService.findAllByLivesIdLazyToEagerOrderByTagsByPage(LIVE_ID, PageRequest.of(0, 5)).get();
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testFindAllByTagsIdLazyToEagerOrderByLivesByPage() {
		List<TagsLives> tagsLives = tagsLivesService.findAllByTagsIdLazyToEagerOrderByLivesByPage(TAG_ID, PageRequest.of(0, 5)).get();
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testFindByLivesIdAndTagsIdLazyToEager() {
		Optional<TagsLives> tagsLives = tagsLivesService.findByLivesIdAndTagsIdLazyToEager(LIVE_ID, TAG_ID);
		assertTrue(tagsLives.isPresent());
	}	

}
