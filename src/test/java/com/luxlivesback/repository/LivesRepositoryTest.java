package com.luxlivesback.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.luxlivesback.model.Usuarios;
import com.luxlivesback.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LivesRepositoryTest {
	
	@Autowired
	private LivesRepository livesRepository;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	private static final Long ID = Long.valueOf(1);
	
	@Before
	public void setUp() throws Exception {
		this.livesRepository.save(obterLive1());
		this.livesRepository.save(obterLive2());
	}
	
	@After
	public final void tearDown() {
		this.livesRepository.deleteAll();
		this.usuariosRepository.deleteAll();
	}	
	
	@Test
	public void testFindAllLazyToEagerByPage() {
		List<Lives> lives = livesRepository.findAllLazyToEagerByPage(PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = livesRepository.findAllLazyToEagerOrderByIdDescByPage(PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByTituloByPage() {
		List<Lives> lives = livesRepository.findAllLazyToEagerOrderByTituloByPage(PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByGeneroByPage() {
		List<Lives> lives = livesRepository.findAllLazyToEagerOrderByGeneroByPage(PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByUsuariosByPage() {
		List<Lives> lives = livesRepository.findAllLazyToEagerOrderByUsuariosByPage(PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByDataIniDescByPage() {
		List<Lives> lives = livesRepository.findAllLazyToEagerOrderByDataIniDescByPage(PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByTituloLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = livesRepository.findAllByTituloLazyToEagerOrderByIdDescByPage("teste", PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByGeneroLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = livesRepository.findAllByGeneroLazyToEagerOrderByIdDescByPage("teste", PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByUsuariosIdLazyToEagerOrderByIdDescByPage() {
		List<Lives> lives = livesRepository.findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(ID, PageRequest.of(0, 5));
		boolean x = lives.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindByIdLazyToEager() {
		Lives lives = livesRepository.findByIdLazyToEager(ID);
		assertNotNull(lives);
	}
	
	private Lives obterLive1() {
		Usuarios usuario = new Usuarios();
		usuario.setNome("Teste Teste");
		usuario.setLogin("teste");
		usuario.setEmail("teste@teste.com");
		usuario.setPassword(PasswordUtils.gerarBCrypt("teste"));
		usuario.setTipo("ADMIN");
		usuario = usuariosRepository.save(usuario);
		
		Lives live = new Lives();
		live.setTitulo("teste");
		live.setGenero("teste");
		live.setUsuarios(usuario);
		return live;
	}
	
	private Lives obterLive2() {
		Usuarios usuario = new Usuarios();
		usuario.setNome("Teste2 Teste");
		usuario.setLogin("teste2");
		usuario.setEmail("teste2@teste.com");
		usuario.setPassword(PasswordUtils.gerarBCrypt("teste"));
		usuario.setTipo("ADMIN");
		usuario = usuariosRepository.save(usuario);
		
		Lives live = new Lives();
		live.setTitulo("teste2");
		live.setGenero("teste2");
		live.setUsuarios(usuario);
		return live;
	}

}
