package com.luxlivesback.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxlivesback.model.TiposTransacao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TiposTransacaoRepositoryTest {
	
	@Autowired
	private TiposTransacaoRepository tiposTransacaoRepository;
	
	private static final String NOME = "LuxLives";
	
	@Before
	public void setUp() throws Exception {
		
		TiposTransacao tipoTransacao = new TiposTransacao();
		tipoTransacao.setNome("LuxLives");
		this.tiposTransacaoRepository.save(tipoTransacao);
		
		TiposTransacao tipoTransacao2 = new TiposTransacao();
		tipoTransacao2.setNome("Dinheiro");
		this.tiposTransacaoRepository.save(tipoTransacao2);
	}
	
	@After
	public final void tearDown() {
		this.tiposTransacaoRepository.deleteAll();
	}
	
	@Test
	public void testFindByNomeIgnoreCase() {
		TiposTransacao tipoTransacao = this.tiposTransacaoRepository.findByNomeIgnoreCase("LuXlIvEs");
		assertEquals(NOME, tipoTransacao.getNome());
	}
	
	@Test
	public void testFindAllByOrderByNome() {
		List<TiposTransacao> tipoTransacao = this.tiposTransacaoRepository.findAllByOrderByNome();
		assertNotNull(tipoTransacao);
	}

}
