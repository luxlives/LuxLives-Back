package com.luxlivesback.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * O teste de PasswordUtils não depende de classes de contexto do String,
 * não necessitando de Anotações.
 * 
 * */

public class PasswordUtilsTest {
	/**
	 * Criamos uma senha para teste.
	 * 
	 * */
	
	private static final String SENHA = "123456";
	
	/**
	 * Criamos uma instância de BCryptPasswordEncoder para validar o hash da senha.
	 * 
	 * */
	
	private final BCryptPasswordEncoder bCrytEncoder = new BCryptPasswordEncoder();
	
	/**
	 * TESTE_01 -> O primeiro teste irá validar senhas nulas, tendo como retorno valor null.
	 * 
	 * */
	
	@Test
	public void testSenhaNula() throws Exception {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}
	
	/**
	 * TESTE_02 -> O segundo teste irá receber uma senha ao qual através do método gerarBCrypt() 
	 * o mesmo será criptografado em um hash. esse hash gerado será comparado com a constante SENHA
	 * retornando true.
	 *  
	 * */
	
	@Test
	public void testGerarHashSenha() throws Exception {
		String hash = PasswordUtils.gerarBCrypt(SENHA);
		assertTrue(bCrytEncoder.matches(SENHA, hash));
	}

}
