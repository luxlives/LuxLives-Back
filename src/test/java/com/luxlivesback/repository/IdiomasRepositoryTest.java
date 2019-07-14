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

import com.luxlivesback.model.Idiomas;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IdiomasRepositoryTest {
	
	@Autowired
	private IdiomasRepository idiomasRepository;
	
	private static final String LINGUA = "LuxLives";
	
	@Before
	public void setUp() throws Exception {
		
		Idiomas idioma = new Idiomas();
		idioma.setLingua("LuxLives");
		this.idiomasRepository.save(idioma);
		
		Idiomas idioma2 = new Idiomas();
		idioma2.setLingua("Português");
		this.idiomasRepository.save(idioma2);
	}
	
	@After
	public final void tearDown() {
		this.idiomasRepository.deleteAll();
	}
	
	@Test
	public void testFindByLinguaIgnoreCase() {
		Idiomas idioma = this.idiomasRepository.findByLinguaIgnoreCase("LuXliVes");
		assertEquals(LINGUA, idioma.getLingua());
	}
	
	@Test
	public void testFindAllByOrderByLingua() {
		List<Idiomas> idiomas = this.idiomasRepository.findAllByOrderByLingua();
		boolean x = idiomas.size() > 0 ? true : false;
		assertTrue(x);
	}

}
