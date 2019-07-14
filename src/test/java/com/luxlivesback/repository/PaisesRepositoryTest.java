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

import com.luxlivesback.model.Paises;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaisesRepositoryTest {
	
	@Autowired
	private PaisesRepository paisesRepository;
	
	private static final String NOME = "LuxLives";
	
	@Before
	public void setUp() throws Exception {
		
		Paises pais = new Paises();
		pais.setNome("LuxLives");
		this.paisesRepository.save(pais);
		
		Paises pais2 = new Paises();
		pais2.setNome("Brasil");
		this.paisesRepository.save(pais2);
	}
	
	@After
	public final void tearDown() {
		this.paisesRepository.deleteAll();
	}
	
	@Test
	public void testFindByNome() {
		Paises pais = this.paisesRepository.findByNomeIgnoreCase("LuxLives");
		assertEquals(NOME, pais.getNome());
	}
	
	@Test
	public void testFindByNomeIgnoreCase() {
		Paises pais = this.paisesRepository.findByNomeIgnoreCase("lUxlIVes");
		assertEquals(NOME, pais.getNome());
	}
	
	@Test
	public void testFindAllByOrderByNome() {
		List<Paises> paises = this.paisesRepository.findAllByOrderByNome();
		boolean x = paises.size() > 0 ? true : false;
		assertTrue(x);		
	}

}
