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

import com.luxlivesback.model.Paises;
import com.luxlivesback.repository.PaisesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaisesServiceTest {
	
	@MockBean
	private PaisesRepository paisesRepository;
	
	@Autowired
	private PaisesService paisesService;
	
	private static final String NOME = "LuxLives";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.paisesRepository.save(Mockito.any(Paises.class))).willReturn(new Paises());
		BDDMockito.given(this.paisesRepository.findByNomeIgnoreCase(Mockito.anyString())).willReturn(new Paises());
		BDDMockito.given(this.paisesRepository.findAllByOrderByNome()).willReturn(new ArrayList<Paises>());
	}
	
	@Test
	public void testSave() {
		Paises pais = this.paisesService.save(new Paises());
		assertNotNull(pais);
	}
	
	@Test
	public void testFindByNome() {
		Optional<Paises> pais = this.paisesService.findByNomeIgnoreCase(NOME);
		assertTrue(pais.isPresent());
	}
	
	@Test
	public void testFindAllByOrderByNome() {
		Optional<List<Paises>> paises = this.paisesService.findAllByOrderByNome();
		assertNotNull(paises.get());		
	}
	
	
	
	

}
