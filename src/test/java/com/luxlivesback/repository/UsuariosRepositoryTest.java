package com.luxlivesback.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxlivesback.model.Usuarios;
import com.luxlivesback.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuariosRepositoryTest {
	
	@Autowired
	private UsuariosRepository usuariosRepository;	
	
	private static final String CPF = "457.081.060-85";
	private static final String LOGIN = "teste";
	private static final String EMAIL = "teste@teste.com";
	
	@Before
	public void setUp() throws Exception {		
		this.usuariosRepository.save(obterUsuario1());		
		this.usuariosRepository.save(obterUsuario2());
	}
	
	@After
	public final void tearDown() {
		this.usuariosRepository.deleteAll();
	}
	
	@Test
	public void testFindByCpfValid() {
		Usuarios usuario = this.usuariosRepository.findByCpf("457.081.060-85");
		assertEquals(CPF, usuario.getCpf());		
	}
	
	@Test
	public void testFindByLoginValid() {
		Usuarios usuario = this.usuariosRepository.findByLogin("teste");
		assertEquals(LOGIN, usuario.getLogin());		
	}	
	
	@Test
	public void testFindByEmailValid() {
		Usuarios usuario = this.usuariosRepository.findByEmail("teste@teste.com");
		assertEquals(EMAIL, usuario.getEmail());		
	}
	
	@Test
	public void testFindByCpfInvalid() {
		Usuarios usuario = this.usuariosRepository.findByCpf("457.081.060-86");
		assertNull(usuario);
	}
	
	@Test
	public void testFindByLoginInvalid() {
		Usuarios usuario = this.usuariosRepository.findByLogin("naoexiste");
		assertNull(usuario);	
	}
	
	@Test
	public void testFindByEmailInvalid() {
		Usuarios usuario = this.usuariosRepository.findByEmail("naoexiste@teste.com");
		assertNull(usuario);		
	}
	
	@Test
	public void testFindAllByPage() {
		List<Usuarios> usuarios = this.usuariosRepository.findAllByPage(PageRequest.of(0, 5));
		boolean x = usuarios.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeStartingWithIgnoreCaseOrderByNomeValid() {
		Page<Usuarios> usuarios = this.usuariosRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome("teste", PageRequest.of(0, 5));
		boolean x = usuarios.getSize() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void test() {
		List<Usuarios> usuarios = this.usuariosRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome("teste");
		boolean x = usuarios.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	private Usuarios obterUsuario1() {
		Usuarios usuario = new Usuarios();
		usuario.setNome("Teste Teste");
		usuario.setLogin("teste");
		usuario.setEmail("teste@teste.com");
		usuario.setPassword(PasswordUtils.gerarBCrypt("teste"));
		usuario.setTipo("ADMIN");
		return usuario;
	}
	
	private Usuarios obterUsuario2() {
		Usuarios usuario = new Usuarios();
		usuario.setNome("Teste2 Teste");
		usuario.setLogin("teste2");
		usuario.setEmail("teste2@teste.com");
		usuario.setPassword(PasswordUtils.gerarBCrypt("teste"));
		usuario.setTipo("ADMIN");
		usuario.setCpf(CPF);
		return usuario;
	}

}
