package com.luxlivesback.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.luxlivesback.model.Usuarios;

public class JwtUserFactory {
	
	private JwtUserFactory() {}
	
	/**
	 * Converte e gera um JwtUser com base nos dados de um Usuario.
	 * 
	 * @param Usuarios
	 * @return JwtUser
	 * */
	
	public static JwtUser create(Usuarios usuario) {
		
		return new JwtUser(
				usuario.getId(),
				usuario.getEmail(),
				usuario.getPassword(),
				
				mapToGrantedAuthorities("ROLE_" + usuario.getTipo().toString()));				
	}
	
	/**
	 * Converte o perfil do divulgador para o formato utilizado pelo String Security
	 * 
	 * @param String tipoEnum
	 * @return List<GrantedAuthority>
	 * */
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(String tipo) {
		System.out.println("JwtUserFactory: " + tipo);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(tipo));
		return authorities;		
	}
}
