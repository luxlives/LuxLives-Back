package com.luxlivesback.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {
	
private static final Logger log =  LoggerFactory.getLogger(PasswordUtils.class);
	
	public PasswordUtils(){}
	
	/**
	 * Gera um hash utilizando BCrypt.
	 * Foi utilizado static para não ter a necessidade 
	 * de instânciar essa classe para utilizar esse método.
	 * 
	 * @param String senha;
	 * @return String senha codificada
	 * 
	 * */
	
	public static String gerarBCrypt(String senha) {
		
		/**
		 * Se a variável for nula, ou seja, não possuir valor; é retornado
		 * a variável vazia/nula.
		 * 
		 * */
		
		if (senha == null) {
			return senha;
		}
		
		/**
		 * Caso contrário, retorna o valor já criptografado pelo método encode()
		 * pertencente a biblioteca BCryptPasswordEncoder.
		 * Esse método de criptografia gera hash diferentes a cada chamada, ou senha, 
		 * uma senha de valor "123456" podem possuir hash´s diferentes onde so o método
		 * matches() pode identificar.
		 * 
		 * */
		
		log.info("Gerar hash com o BCrypt.");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha); 
	}
	
	/**
	 * Verifica se a senha é válida.
	 * 
	 * @param String Senha
	 * @param String Senha codificada
	 * @return boolean
	 * 
	 * */
	
	public static boolean senhaValida(String senha, String senhaEncoded) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(senha, senhaEncoded);
	}	
}

