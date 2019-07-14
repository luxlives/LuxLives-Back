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

import com.luxlivesback.model.TiposTransacao;
import com.luxlivesback.repository.TiposTransacaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TiposTransacaoServiceTest {
	
	@MockBean
	private TiposTransacaoRepository tiposTransacaoRepository;
	
	@Autowired
	private TiposTransacaoService tiposTransacaoService;
	
	private static final String NOME = "LuxLives";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.tiposTransacaoRepository.save(Mockito.any(TiposTransacao.class))).willReturn(new TiposTransacao());
		BDDMockito.given(this.tiposTransacaoRepository.findByNomeIgnoreCase(Mockito.anyString())).willReturn(new TiposTransacao());
		BDDMockito.given(this.tiposTransacaoRepository.findAllByOrderByNome()).willReturn(new ArrayList<TiposTransacao>());
	}
	
	@Test
	public void testSave() {
		TiposTransacao tiposTransacao = this.tiposTransacaoService.save(new TiposTransacao());
		assertNotNull(tiposTransacao);
	}
	
	@Test
	public void testFindByNomeIgnoreCase() {
		Optional<TiposTransacao> tipoTransacao = this.tiposTransacaoService.findByNomeIgnoreCase(NOME);
		assertTrue(tipoTransacao.isPresent());
	}
	
	@Test
	public void testFindAllByOrderByNome() {
		Optional<List<TiposTransacao>> tiposTransacao = this.tiposTransacaoService.findAllByOrderByNome();
		assertNotNull(tiposTransacao.get());
	}

}
