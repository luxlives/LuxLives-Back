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

import com.luxlivesback.model.ComprasExtrato;
import com.luxlivesback.repository.ComprasExtratoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComprasExtratoServiceTest {
	
	@MockBean
	private ComprasExtratoRepository comprasExtratoRepository;
	
	@Autowired
	private ComprasExtratoService comprasExtratoService;
	
	private static final Long ID = Long.valueOf(1);
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.comprasExtratoRepository.findAllLazyToEagerByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<ComprasExtrato>());
		BDDMockito.given(this.comprasExtratoRepository.findAllLazyToEagerOrderByDataTransacaoDescByPage(Mockito.any(PageRequest.class))).willReturn(new ArrayList<ComprasExtrato>());
		BDDMockito.given(this.comprasExtratoRepository.findAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage(Mockito.anyLong(),Mockito.any(PageRequest.class))).willReturn(new ArrayList<ComprasExtrato>());		
		BDDMockito.given(this.comprasExtratoRepository.findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Mockito.anyLong(),Mockito.anyLong(),Mockito.any(PageRequest.class))).willReturn(new ArrayList<ComprasExtrato>());
		BDDMockito.given(this.comprasExtratoRepository.findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(Mockito.anyLong(),Mockito.anyLong(),Mockito.any(PageRequest.class))).willReturn(new ArrayList<ComprasExtrato>());
		BDDMockito.given(this.comprasExtratoRepository.findAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong(),Mockito.any(PageRequest.class))).willReturn(new ArrayList<ComprasExtrato>());
		BDDMockito.given(this.comprasExtratoRepository.findByIdLazyToEager(Mockito.anyLong())).willReturn(new ComprasExtrato());
	}
	
	@Test
	public void testFindAllLazyToEagerByPage() {
		List<ComprasExtrato> comprasExtrato = comprasExtratoService.findAllLazyToEagerByPage(PageRequest.of(0, 5)).get();
		assertNotNull(comprasExtrato);
	}
	
	@Test
	public void testFindAllLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = comprasExtratoService.findAllLazyToEagerOrderByDataTransacaoDescByPage(PageRequest.of(0, 5)).get();
		assertNotNull(comprasExtrato);
	}
	
	@Test
	public void testFindAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = comprasExtratoService.findAllByUsuariosIdLazyToEagerOrderByDataTransacaoDescByPage(ID, PageRequest.of(0, 5)).get();
		assertNotNull(comprasExtrato);
	}	
	
	@Test
	public void testFindAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = comprasExtratoService.findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(ID, ID, PageRequest.of(0, 5)).get();
		assertNotNull(comprasExtrato);
	}
	
	@Test
	public void testFindAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = comprasExtratoService.findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(ID, ID, PageRequest.of(0, 5)).get();
		assertNotNull(comprasExtrato);
	}	
	
	@Test
	public void testFindAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage() {
		List<ComprasExtrato> comprasExtrato = comprasExtratoService.findAllByUsuariosIdAndProdutosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(ID, ID, ID, PageRequest.of(0, 5)).get();
		assertNotNull(comprasExtrato);
	}
	
	@Test
	public void testFindByIdLazyToEager() {
		Optional<ComprasExtrato> comprasExtrato = comprasExtratoService.findByIdLazyToEager(ID);
		assertTrue(comprasExtrato.isPresent());
	}

}
