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

import com.luxlivesback.model.Produtos;
import com.luxlivesback.repository.ProdutosRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutosServiceTest {
	
	@MockBean
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private ProdutosService produtosService;
	
	private static final String NOME = "Teste";
	private static final Boolean STATUS = true;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.produtosRepository.save(Mockito.any(Produtos.class))).willReturn(new Produtos());
		BDDMockito.given(this.produtosRepository.findAllByStatus(Mockito.anyBoolean())).willReturn(new ArrayList<Produtos>());		
		BDDMockito.given(this.produtosRepository.findAllByStatusOrderByIdAscByPage(Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByStatusOrderByNomeAscByPage(Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByStatusOrderByNomeDescByPage(Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByStatusOrderByQtdTokensAscByPage(Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByStatusOrderByQtdTokensDescByPage(Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByStatusOrderByPrecoReaisAscByPage(Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByStatusOrderByPrecoReaisDescByPage(Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());		
		BDDMockito.given(this.produtosRepository.findAllByNomeAndStatusOrderByIdAscByPage(Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByNomeAndStatusOrderByNomeAscByPage(Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByNomeAndStatusOrderByNomeDescByPage(Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByNomeAndStatusOrderByQtdTokensAscByPage(Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByNomeAndStatusOrderByQtdTokensDescByPage(Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisAscByPage(Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisDescByPage(Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(PageRequest.class))).willReturn(new ArrayList<Produtos>());
		BDDMockito.given(this.produtosRepository.findByNomeIgnoreCase(Mockito.anyString())).willReturn(new Produtos());
	}
	
	@Test
	public void testSave() {
		Produtos produtos = this.produtosService.save(new Produtos());
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByStatus() {
		List<Produtos> produtos = produtosService.findAllByStatus(STATUS).get();
		assertNotNull(produtos);
	}	
	
	@Test
	public void testFindAllByStatusOrderByIdAscByPage() {
		List<Produtos> produtos = produtosService.findAllByStatusOrderByIdAscByPage(STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByStatusOrderByNomeAscByPage() {
		List<Produtos> produtos = produtosService.findAllByStatusOrderByNomeAscByPage(STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByStatusOrderByNomeDescByPage() {
		List<Produtos> produtos = produtosService.findAllByStatusOrderByNomeDescByPage(STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByStatusOrderByQtdTokensAscByPage() {
		List<Produtos> produtos = produtosService.findAllByStatusOrderByQtdTokensAscByPage(STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByStatusOrderByQtdTokensDescByPage() {
		List<Produtos> produtos = produtosService.findAllByStatusOrderByQtdTokensDescByPage(STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByStatusOrderByPrecoReaisAscByPage() {
		List<Produtos> produtos = produtosService.findAllByStatusOrderByPrecoReaisAscByPage(STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByStatusOrderByPrecoReaisDescByPage() {
		List<Produtos> produtos = produtosService.findAllByStatusOrderByPrecoReaisDescByPage(STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}	
	
	@Test
	public void testFindAllByNomeAndStatusOrderByIdAscByPage() {
		List<Produtos> produtos = produtosService.findAllByNomeAndStatusOrderByIdAscByPage(NOME, STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByNomeAscByPage() {
		List<Produtos> produtos = produtosService.findAllByNomeAndStatusOrderByNomeAscByPage(NOME, STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByNomeDescByPage() {
		List<Produtos> produtos = produtosService.findAllByNomeAndStatusOrderByNomeDescByPage(NOME, STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByQtdTokensAscByPage() {
		List<Produtos> produtos = produtosService.findAllByNomeAndStatusOrderByQtdTokensAscByPage(NOME, STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByQtdTokensDescByPage() {
		List<Produtos> produtos = produtosService.findAllByNomeAndStatusOrderByQtdTokensDescByPage(NOME, STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByPrecoReaisAscByPage() {
		List<Produtos> produtos = produtosService.findAllByNomeAndStatusOrderByPrecoReaisAscByPage(NOME, STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByPrecoReaisDescByPage() {
		List<Produtos> produtos = produtosService.findAllByNomeAndStatusOrderByPrecoReaisDescByPage(NOME, STATUS, PageRequest.of(0, 5)).get();
		assertNotNull(produtos);
	}
	
	@Test
	public void testFindByNomeIgnoreCase() {
		Optional<Produtos> produto = produtosService.findByNomeIgnoreCase("Teste Produto1");
		assertTrue(produto.isPresent());
	}

}
