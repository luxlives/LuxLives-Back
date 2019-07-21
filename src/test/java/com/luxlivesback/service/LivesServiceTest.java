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

import com.luxlivesback.model.Lives;
import com.luxlivesback.repository.LivesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LivesServiceTest {
	
	@MockBean
	private LivesRepository livesRepository;
	
	@Autowired
	private LivesService livesService;
	
	private static final Long ID = Long.valueOf(1);
	private static final Long USUARIO_ID = Long.valueOf(1);
	private static final String GENERO = "teste";
	private static final String TITULO = "teste";
	
	@Before
	public void setUp() throws Exception {
		//BDDMockito.given(this.livesRepository.save(Mockito.any(Lives.class))).willReturn(new Lives());
		BDDMockito.given(this.livesRepository.findAllLazyToEagerByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());
		BDDMockito.given(this.livesRepository.findAllLazyToEagerOrderByIdDescByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());
		BDDMockito.given(this.livesRepository.findAllLazyToEagerOrderByTituloByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());		
		BDDMockito.given(this.livesRepository.findAllLazyToEagerOrderByGeneroByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());
		BDDMockito.given(this.livesRepository.findAllLazyToEagerOrderByUsuariosByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());
		BDDMockito.given(this.livesRepository.findAllLazyToEagerOrderByDataIniDescByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());		
		BDDMockito.given(this.livesRepository.findAllByTituloLazyToEagerOrderByIdDescByPage(Mockito.anyString(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());
		BDDMockito.given(this.livesRepository.findAllByGeneroLazyToEagerOrderByIdDescByPage(Mockito.anyString(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());
		BDDMockito.given(this.livesRepository.findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(Mockito.anyLong(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Lives>());
		BDDMockito.given(this.livesRepository.findByIdLazyToEager(Mockito.anyLong())).willReturn(new Lives());
		
	}
	
	/*@Test
	public void testSave() {
		Lives lives = this.livesService.save(new Lives());
		assertNotNull(lives);
	}*/
	
	@Test
	public void testFindAllLazyToEagerByPage() {
		List<Lives> lives = this.livesService.findAllLazyToEagerByPage(PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = this.livesService.findAllLazyToEagerOrderByIdDescByPage(PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByTituloByPage() {
		List<Lives> lives = this.livesService.findAllLazyToEagerOrderByTituloByPage(PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}	
	
	@Test
	public void testFindAllLazyToEagerOrderByGeneroByPage() {
		List<Lives> lives = this.livesService.findAllLazyToEagerOrderByGeneroByPage(PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByUsuariosByPage() {
		List<Lives> lives = this.livesService.findAllLazyToEagerOrderByUsuariosByPage(PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByDataIniDescByPage() {
		List<Lives> lives = this.livesService.findAllLazyToEagerOrderByDataIniDescByPage(PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}	
	
	@Test
	public void testFindAllByTituloLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = this.livesService.findAllByTituloLazyToEagerOrderByIdDescByPage(TITULO, PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}
	
	@Test
	public void testFindAllByGeneroLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = this.livesService.findAllByGeneroLazyToEagerOrderByIdDescByPage(GENERO, PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}
	
	@Test
	public void testFindAllByUsuariosIdLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = this.livesService.findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(USUARIO_ID, PageRequest.of(0, 5)).get();
		assertNotNull(lives);
	}
	
	@Test
	public void testFindByIdLazyToEager() {
		Optional<Lives> lives = this.livesService.findByIdLazyToEager(ID);
		assertTrue(lives.isPresent());
	}	

}
