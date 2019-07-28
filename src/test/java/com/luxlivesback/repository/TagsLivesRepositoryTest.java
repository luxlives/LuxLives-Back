package com.luxlivesback.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxlivesback.model.Lives;
import com.luxlivesback.model.Tags;
import com.luxlivesback.model.TagsLives;
import com.luxlivesback.model.Usuarios;
import com.luxlivesback.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TagsLivesRepositoryTest {
	
	@Autowired
	private TagsLivesRepository tagsLivesRepository;
	
	@Autowired
	private LivesRepository livesRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	private static Long LIVE_ID;
	private static Long TAG_ID;
	
	@Before
	public void setUp() throws Exception {
		this.obterTagsLives().forEach(x -> tagsLivesRepository.save(x));
		TagsLives tagsLives = tagsLivesRepository.findAllLazyToEagerOrderByLives().get(0);
		LIVE_ID = tagsLives.getLives().getId();
		TAG_ID = tagsLives.getTags().getId();
	}
	
	@After
	public final void tearDown() {
		tagsLivesRepository.deleteAll();
		tagsRepository.deleteAll();
		livesRepository.deleteAll();
		usuariosRepository.deleteAll();
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByLives() {
		List<TagsLives> tagsLives = tagsLivesRepository.findAllLazyToEagerOrderByLives();
		boolean x = tagsLives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByTags() {
		List<TagsLives> tagsLives = tagsLivesRepository.findAllLazyToEagerOrderByTags();
		boolean x = tagsLives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByLivesIdLazyToEagerOrderByTags() {
		List<TagsLives> tagsLives = tagsLivesRepository.findAllByLivesIdLazyToEagerOrderByTags(LIVE_ID);
		boolean x = tagsLives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByTagsIdLazyToEagerOrderByLives() {
		List<TagsLives> tagsLives = tagsLivesRepository.findAllByTagsIdLazyToEagerOrderByLives(TAG_ID);
		boolean x = tagsLives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByLivesByPage() {
		List<TagsLives> tagsLives = tagsLivesRepository.findAllLazyToEagerOrderByLivesByPage(PageRequest.of(0, 5));
		boolean x = tagsLives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByLivesIdLazyToEagerOrderByTagsByPage() {
		List<TagsLives> tagsLives = tagsLivesRepository.findAllByLivesIdLazyToEagerOrderByTagsByPage(LIVE_ID, PageRequest.of(0, 5));
		boolean x = tagsLives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByTagsIdLazyToEagerOrderByLivesByPage() {
		List<TagsLives> tagsLives = tagsLivesRepository.findAllByTagsIdLazyToEagerOrderByLivesByPage(TAG_ID, PageRequest.of(0, 5));
		boolean x = tagsLives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindByLivesIdAndTagsIdLazyToEager() {
		TagsLives tagsLives = tagsLivesRepository.findByLivesIdAndTagsIdLazyToEager(LIVE_ID, TAG_ID);		
		assertNotNull(tagsLives);
	}
	
	@Test
	public void testDeleteByLivesIdAndTagsId() {		
		try {
			tagsLivesRepository.deleteByLivesIdAndTagsId(LIVE_ID, TAG_ID);
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@Test
	public void testDeleteByLivesId() {		
		try {
			tagsLivesRepository.deleteByLivesId(LIVE_ID);
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@Test
	public void testDeleteByTagsId() {		
		try {
			tagsLivesRepository.deleteByTagsId(TAG_ID);
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	private List<Lives> obterLives() {
		
		List<Lives> lives = new ArrayList<Lives>();
		
		Usuarios usuario = new Usuarios();
		usuario.setNome("Teste Teste");
		usuario.setLogin("teste");
		usuario.setEmail("teste@teste.com");
		usuario.setPassword(PasswordUtils.gerarBCrypt("teste"));
		usuario.setTipo("ADMIN");
		usuario = this.usuariosRepository.save(usuario);		
		Lives live = new Lives();
		live.setTitulo("teste");
		live.setGenero("teste");
		live.setUsuarios(usuario);
		live = this.livesRepository.save(live);
		lives.add(live);
		
		Usuarios usuario2 = new Usuarios();
		usuario2.setNome("Teste2 Teste");
		usuario2.setLogin("teste2");
		usuario2.setEmail("teste2@teste.com");
		usuario2.setPassword(PasswordUtils.gerarBCrypt("teste"));
		usuario2.setTipo("ADMIN");
		usuario2 = this.usuariosRepository.save(usuario2);		
		Lives live2 = new Lives();
		live2.setTitulo("teste2");
		live2.setGenero("teste2");
		live2.setUsuarios(usuario2);
		live2 = this.livesRepository.save(live2);
		lives.add(live2);
		
		return lives;
		
	}
	
	private List<Tags> obterTags() {
		
		List<Tags> tags = new ArrayList<Tags>();
		
		Tags tag = new Tags();
		tag.setNome("Loira");
		tag.setContador((long) 0);
		this.tagsRepository.save(tag);
		tags.add(tag);
		
		Tags tag2 = new Tags();
		tag2.setNome("Morena");
		tag2.setContador((long) 0);
		this.tagsRepository.save(tag2);
		tags.add(tag2);
		
		Tags tag3 = new Tags();
		tag3.setNome("Ruiva");
		tag3.setContador((long) 0);
		this.tagsRepository.save(tag3);
		tags.add(tag3);
		
		return tags;
	}
	
	private List<TagsLives> obterTagsLives() {
		
		List<TagsLives> tagsLives = new ArrayList<TagsLives>();		
		List<Lives> lives = this.obterLives();
		List<Tags> tags = this.obterTags();
		
		for (Lives live : lives)
			for (Tags tag : tags) {
				TagsLives tagLive = new TagsLives();
				tagLive.setLives(live);
				tagLive.setTags(tag);
				tagsLives.add(tagLive);
			}
		
		return tagsLives;
	}
}
