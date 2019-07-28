package com.luxlivesback.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.luxlivesback.model.ComprasExtrato;
import com.luxlivesback.model.Produtos;
import com.luxlivesback.model.TiposTransacao;
import com.luxlivesback.model.Usuarios;
import com.luxlivesback.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComprasExtratoRepositoryTest {
	
	@Autowired
	private ComprasExtratoRepository comprasExtratoRepository;
	
	@Autowired
	private UsuariosRepository usuariosRepository;	
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private TiposTransacaoRepository tiposTransacaoRepository;
	
	private static Long ID;
	private static Long USER_ID;
	private static Long PRODUTO_ID;
	private static Long TIPOSTRANSACAO_ID;
	
	@Before
	public void setUp() throws Exception {		
		List<ComprasExtrato> comprasExtrato = this.obterComprasExtrato();
		comprasExtrato.forEach(x -> this.comprasExtratoRepository.save(x));
		ID = comprasExtratoRepository.findAll().get(0).getId();
	}
	
	@After
	public final void tearDown() {
		this.comprasExtratoRepository.deleteAll();
		this.tiposTransacaoRepository.deleteAll();
		this.produtosRepository.deleteAll();
		this.usuariosRepository.deleteAll();
	}
	
	@Test
	public void testFindAllLazyToEagerByPage() {
		List<ComprasExtrato> comprasExtrato = this.comprasExtratoRepository.findAllLazyToEagerByPage(PageRequest.of(0, 5));
		boolean x = comprasExtrato.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = this.comprasExtratoRepository.findAllLazyToEagerOrderByDataTransacaoDescByPage(PageRequest.of(0, 5));
		boolean x = comprasExtrato.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = this.comprasExtratoRepository.findAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage(USER_ID, PageRequest.of(0, 5));
		boolean x = comprasExtrato.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = this.comprasExtratoRepository.findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(USER_ID, TIPOSTRANSACAO_ID, PageRequest.of(0, 5));
		boolean x = comprasExtrato.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = this.comprasExtratoRepository.findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(USER_ID, PRODUTO_ID, PageRequest.of(0, 5));
		boolean x = comprasExtrato.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = this.comprasExtratoRepository.findAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(USER_ID, PRODUTO_ID, TIPOSTRANSACAO_ID, PageRequest.of(0, 5));
		boolean x = comprasExtrato.size() > 0 ? true : false;
		assertTrue(x);
	}
	
	@Test
	public void testFindByIdLazyToEager() {
		ComprasExtrato comprasExtrato = comprasExtratoRepository.findByIdLazyToEager(ID);
		assertNotNull(comprasExtrato);
	}	
	
	private List<ComprasExtrato> obterComprasExtrato() {
		
		Usuarios usuario = new Usuarios();
		usuario.setNome("Teste Teste");
		usuario.setLogin("teste");
		usuario.setEmail("teste@teste.com");
		usuario.setPassword(PasswordUtils.gerarBCrypt("teste"));
		usuario.setTipo("ADMIN");
		usuario = usuariosRepository.save(usuario);
		USER_ID = usuario.getId();
		
		Produtos produto = new Produtos();
		produto.setNome("Teste Produto1");
		produto.setPrecoReais(BigDecimal.valueOf(100));
		produto.setQtdTokens(Long.valueOf(100));
		produto.setStatus(true);
		produto = produtosRepository.save(produto);
		PRODUTO_ID = produto.getId();
		
		TiposTransacao tipoTransacao = new TiposTransacao();
		tipoTransacao.setNome("LuxLives");
		tipoTransacao = tiposTransacaoRepository.save(tipoTransacao);
		TIPOSTRANSACAO_ID = tipoTransacao.getId();
		
		ComprasExtrato comprasExtrato1 = new ComprasExtrato();
		comprasExtrato1.setEntrada(true);
		comprasExtrato1.setUsuarios(usuario);
		comprasExtrato1.setProdutos(produto);
		comprasExtrato1.setTipoTransacao(tipoTransacao);
		comprasExtrato1.setValor(BigDecimal.valueOf(100));
		
		ComprasExtrato comprasExtrato2 = new ComprasExtrato();
		comprasExtrato2.setEntrada(true);
		comprasExtrato2.setUsuarios(usuario);
		comprasExtrato2.setProdutos(produto);
		comprasExtrato2.setTipoTransacao(tipoTransacao);
		comprasExtrato2.setValor(BigDecimal.valueOf(100));
		
		ComprasExtrato comprasExtrato3 = new ComprasExtrato();
		comprasExtrato3.setEntrada(true);
		comprasExtrato3.setUsuarios(usuario);
		comprasExtrato3.setProdutos(produto);
		comprasExtrato3.setTipoTransacao(tipoTransacao);
		comprasExtrato3.setValor(BigDecimal.valueOf(100));
		
		List<ComprasExtrato> comprasExtrato = new ArrayList<ComprasExtrato>();
		comprasExtrato.add(comprasExtrato1);
		comprasExtrato.add(comprasExtrato2);
		comprasExtrato.add(comprasExtrato3);
		
		return comprasExtrato;
	}

}
