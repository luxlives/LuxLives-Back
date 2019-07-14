package com.luxlivesback.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxlivesback.model.Tags;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TagsRepositoryTest {
	
	@Autowired
	private TagsRepository tagsRepository;
	
	private static final String NOME = "LuxLives";
	
	@Before
	public void setUp() throws Exception {
		
		Tags tag = new Tags();
		tag.setNome("LuxLives");
		tag.setContador((long) 0);
		this.tagsRepository.save(tag);
		
		Tags tag2 = new Tags();
		tag2.setNome("Mulheres");
		tag2.setContador((long) 0);
		this.tagsRepository.save(tag2);
	}
	
	@After
	public final void tearDown() {
		tagsRepository.deleteAll();
	}
	
	@Test
	public void testFindByNome() {
		Tags tag = this.tagsRepository.findByNomeIgnoreCase("LuxLives");
		assertEquals(NOME, tag.getNome());
	}
	
	@Test
	public void testFindByNomeIgnoreCase() {
		Tags tag = this.tagsRepository.findByNomeIgnoreCase("lUxlIVes");
		assertEquals(NOME, tag.getNome());
	}
	
	@Test
	public void testFindAllByNomeStartingWithIgnoreCaseOrderByNome() {
		List<Tags> tags = this.tagsRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome("LuxLives");
		boolean x = tags.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByOrderByNome() {
		List<Tags> tags = this.tagsRepository.findAllByOrderByNome();
		boolean x = tags.size() > 0 ? true : false;
		assertTrue(x);
	}

}
