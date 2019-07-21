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
		List<Produtos> produtos = produtosRepository.findAllByStatus(true);
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByIdAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByIdAscByPage(true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByNomeAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByNomeAscByPage(true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByNomeDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByNomeDescByPage(true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByQtdTokensAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByQtdTokensAscByPage(true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByQtdTokensDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByQtdTokensDescByPage(true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByPrecoReaisAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByPrecoReaisAscByPage(true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByStatusOrderByPrecoReaisDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByStatusOrderByPrecoReaisDescByPage(true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}	
	
	@Test
	public void testFindAllByNomeAndStatusOrderByIdAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByIdAscByPage("Teste", true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByNomeAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByNomeAscByPage("Teste", true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByNomeDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByNomeDescByPage("Teste", true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByQtdTokensAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByQtdTokensAscByPage("Teste", true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByQtdTokensDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByQtdTokensDescByPage("Teste", true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByPrecoReaisAscByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisAscByPage("Teste", true, PageRequest.of(0, 5));
		boolean x = produtos.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByNomeAndStatusOrderByPrecoReaisDescByPage() {
		List<Produtos> produtos = produtosRepository.findAllByNomeAndStatusOrderByPrecoReaisDescByPage("Teste", true, PageRequest.of(0, 5));
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
