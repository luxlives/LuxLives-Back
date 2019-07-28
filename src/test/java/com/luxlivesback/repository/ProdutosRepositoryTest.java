package com.luxlivesback.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
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

import com.luxlivesback.model.Produtos;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutosRepositoryTest {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	private static final String NOME = "Teste";
	private static final Boolean STATUS = true;
	
	@Before
	public void setUp() throws Exception {
		this.produtosRepository.save(obterProduto1());
		this.produtosRepository.save(obterProduto2());
	}
	
	@After
	public final void tearDown() {
		this.produtosRepository.deleteAll();
	}
	
	@Test
	public void testFindAllByStatus() {
		List<Produtos> produtos = produtosRepository.findAllByStatus(STATUS);
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByIdAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByIdAscByPage(STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByNomeAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByNomeAscByPage(STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByNomeDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByNomeDescByPage(STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByQtdTokensAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByQtdTokensAscByPage(STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByQtdTokensDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByQtdTokensDescByPage(STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByPrecoReaisAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByPrecoReaisAscByPage(STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByPrecoReaisDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByPrecoReaisDescByPage(STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}	
	
	@Test
	public void testFindAllByNomeAndStatusOrderByIdAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByIdAscByPage(NOME, STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByNomeAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByNomeAscByPage(NOME, STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByNomeDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByNomeDescByPage(NOME, STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByQtdTokensAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByQtdTokensAscByPage(NOME, STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByQtdTokensDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByQtdTokensDescByPage(NOME, STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByPrecoReaisAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisAscByPage(NOME, STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByPrecoReaisDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisDescByPage(NOME, STATUS, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}	
	
	@Test
	public void findByNomeIgnoreCase() {
		Produtos produtos = produtosRepository.findByNomeIgnoreCase("Teste Produto1");
		assertNotNull(produtos);
	}	
	
	private Produtos obterProduto1() {
		Produtos produto = new Produtos();
		produto.setNome("Teste Produto1");
		produto.setPrecoReais(BigDecimal.valueOf(100));
		produto.setQtdTokens(Long.valueOf(100));
		produto.setStatus(true);
		return produto;
	}
	
	private Produtos obterProduto2() {
		Produtos produto = new Produtos();
		produto.setNome("Teste Produto2");
		produto.setPrecoReais(BigDecimal.valueOf(100));
		produto.setQtdTokens(Long.valueOf(100));
		produto.setStatus(true);
		return produto;
	}

}
