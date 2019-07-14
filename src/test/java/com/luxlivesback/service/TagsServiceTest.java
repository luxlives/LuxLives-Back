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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxlivesback.model.Tags;
import com.luxlivesback.repository.TagsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TagsServiceTest {
	
	@MockBean
	private TagsRepository tagsRepository;
	
	@Autowired
	private TagsService tagsService;	
	
	private static final String NOME = "LuxLives";	
	
	@Before
	public void setUp() throws Exception {		
		BDDMockito.given(this.tagsRepository.save(Mockito.any(Tags.class))).willReturn(new Tags());
		BDDMockito.given(this.tagsRepository.findByNomeIgnoreCase(Mockito.anyString())).willReturn(new Tags());		
		BDDMockito.given(this.tagsRepository.findAllByOrderByNome()).willReturn(new ArrayList<Tags>());
		BDDMockito.given(this.tagsRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome(Mockito.anyString())).willReturn(new ArrayList<Tags>());
	}
	
	@Test
	public void testSave() {
		Tags tags = this.tagsService.save(new Tags());
		assertNotNull(tags);
	}
	
	@Test
	public void testFindByNomeIgnoreCase() {
		Optional<Tags> tags = this.tagsService.findByNomeIgnoreCase(NOME);
		assertTrue(tags.isPresent());
	}	
	
	@Test
	public void testFindAllByOrderByNome() {
		Optional<List<Tags>> tags = this.tagsService.findAllByOrderByNome();
		assertNotNull(tags.get());
	}
	
	@Test
	public void testFindAllByNomeStartingWithIgnoreCaseOrderByNome() {
		Optional<List<Tags>> tags = this.tagsService.findAllByNomeStartingWithIgnoreCaseOrderByNome(NOME);		
		assertNotNull(tags.get());
	}

}
