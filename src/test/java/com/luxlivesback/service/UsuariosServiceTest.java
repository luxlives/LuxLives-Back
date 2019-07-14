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

import com.luxlivesback.model.Usuarios;
import com.luxlivesback.repository.UsuariosRepository;

/**
 * As três anotações abaixo são as configurações defaut de contexto do spring para teste.
 * 
 * */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuariosServiceTest {
	
	@MockBean
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private UsuariosService usuariosService;
	
	private static final String CPF = "457.081.060-85";
	private static final String LOGIN = "teste";
	private static final String EMAIL = "teste@teste.com";
	
	@Before
	public void setUp() throws Exception {		
		BDDMockito.given(this.usuariosRepository.save(Mockito.any(Usuarios.class))).willReturn(new Usuarios());
		BDDMockito.given(this.usuariosRepository.findByCpf(Mockito.anyString())).willReturn(new Usuarios());
		BDDMockito.given(this.usuariosRepository.findByEmail(Mockito.anyString())).willReturn(new Usuarios());
		BDDMockito.given(this.usuariosRepository.findByLogin(Mockito.anyString())).willReturn(new Usuarios());
		BDDMockito.given(this.usuariosRepository.findAllByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<Usuarios>());
		BDDMockito.given(this.usuariosRepository.findAllByNomeStartingWithIgnoreCaseOrderByNome(Mockito.anyString())).willReturn(new ArrayList<Usuarios>());
	}
	
	@Test
	public void testSave() {
		Usuarios usuarios = this.usuariosService.save(new Usuarios());
		assertNotNull(usuarios);		
	}
	
	@Test
	public void testFindByCpfValid() {
		Optional<Usuarios> usuarios = this.usuariosService.findByCpf(CPF);
		assertTrue(usuarios.isPresent());
	}
	
	@Test
	public void testFindByLoginValid() {
		Optional<Usuarios> usuarios = this.usuariosService.findByLogin(LOGIN);
		assertTrue(usuarios.isPresent());
	}
	
	@Test
	public void testFindByEmailValid() {
		Optional<Usuarios> usuarios = this.usuariosService.findByEmail(EMAIL);
		assertTrue(usuarios.isPresent());
	}
	
	@Test
	public void testFindAllByPage() {
		List<Usuarios> usuarios = this.usuariosService.findAllByPage(PageRequest.of(0, 5)).get();
		assertNotNull(usuarios);
	}
	
	@Test
	public void testFindAllByNomeIgnoreCase() {
		List<Usuarios> usuarios = this.usuariosService.findAllByNomeIgnoreCase("teste").get();
		assertNotNull(usuarios);
	}

}
