package com.luxlivesback.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OptionalV2 {
	
	private static final Logger log = LoggerFactory.getLogger(OptionalV2.class);
	
	/**
	 * Existe momentos em que a função 'x.equals(null)' ou a simples
	 * condição 'x != null' não nos retorna true ou false, mais 
	 * sim um erro. Para isso foi criado essa função de verificação
	 * utilizando a metodologia Optional.
	 * */
	
	public static <T>Boolean isPresent(T id) {
		log.info("Verificando existencia de valor: {}", id);
		Optional<T> x= Optional.ofNullable(id);
		if (x.isPresent()) return true;
		return false;
	}
	
	public static <T>Boolean isPresent2(T id) {
		log.info("Verificando existencia de valor: {}", id);
		
		if (id != null) {
			Optional<T> x= Optional.ofNullable(id);
			if (x.isPresent()) return true;
			return false;
		} else {
			return false;
		}
	}
	
	public static <T>Boolean isPresent3(T id) {
		log.info("Verificando existencia de valor: {}", id);
		
		Optional<T> x= Optional.ofNullable(id);
		if (id != null && id.toString().length() > 0 && x.isPresent()) return true;
		return false;
	}
	
	/**
	 * Converte as duas strings em caixa baixa para efetuar uma comparação absoluta.
	 * 
	 * @param String a
	 * @param String b
	 * @return boolean
	 * */
	
	public static Boolean compareStrings(String a, String b) {		
		
		a = a.toLowerCase();
		b = b.toLowerCase();
		
		log.info("Comparando: {} == {}", a , b);
		
		return a.equals(b) ? true : false;		
	}
	
	/**
	 * Formata um atring deixando o primeiro caracter em caixa alta e os demais em caixa baixa.
	 * 
	 * @param String palavra
	 * @return palavra
	 * */
	
	public static String formatString(String palavra) {
		
		palavra = palavra.toLowerCase();
		palavra = palavra.substring(0,1).toUpperCase().concat(palavra.substring(1));
		return palavra;
	}

}
