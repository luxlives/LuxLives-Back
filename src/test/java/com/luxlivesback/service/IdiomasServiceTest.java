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

import com.luxlivesback.model.Idiomas;
import com.luxlivesback.repository.IdiomasRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IdiomasServiceTest {
	
	@MockBean
	private IdiomasRepository idiomasRepository;
	
	@Autowired
	private IdiomasService idiomasService;
	
	private static final String LINGUA = "LuxLives";
	
	@Before
	public void setUp() {
		BDDMockito.given(this.idiomasRepository.save(Mockito.any(Idiomas.class))).willReturn(new Idiomas());
		BDDMockito.given(this.idiomasRepository.findByLinguaIgnoreCase(Mockito.anyString())).willReturn(new Idiomas());
		BDDMockito.given(this.idiomasRepository.findAllByOrderByLingua()).willReturn(new ArrayList<Idiomas>());
	}
	
	@Test
	public void testSave() {
		Idiomas idioma = this.idiomasService.save(new Idiomas());
		assertNotNull(idioma);
	}
	
	@Test
	public void testFindByLinguaIgnoreCase() {
		Optional<Idiomas> idioma = this.idiomasService.findByLinguaIgnoreCase(LINGUA);
		assertTrue(idioma.isPresent());
	}
	
	@Test
	public void testFindAllByOrderByLingua() {
		Optional<List<Idiomas>> idiomas = this.idiomasService.findAllByOrderByLingua();
		assertNotNull(idiomas.get());
	}

}
