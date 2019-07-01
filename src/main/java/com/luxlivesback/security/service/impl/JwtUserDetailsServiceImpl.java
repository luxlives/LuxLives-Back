package com.luxlivesback.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luxlivesback.model.Usuarios;
import com.luxlivesback.repository.UsuariosRepository;
import com.luxlivesback.security.JwtUserFactory;

/**
 * UserDetailsService -> faz parte do core é precisa 
 * ser implementado para obter os dados do usuário.
 * 
 * */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	/**
	 * O username passado por parâmetro , seria nesse caso o email ou login de acesso;
	 * Será verificado se o mesmo existe no banco;
	 * Caso existam será criado um token em JwtUserFactory.
	 * 
	 * @param email ou logind o usuário
	 * @return UserDetails
	 * 
	 * */
	
	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username.contains("@")) {
			
			Optional<Usuarios> usuario = Optional.ofNullable(usuariosRepository.findByEmail(username));			
			if (usuario.isPresent()) return JwtUserFactory.create(usuario.get());
			throw new UsernameNotFoundException("Email não encontrado");
			
		} else {
			
			Optional<Usuarios> usuario = Optional.ofNullable(usuariosRepository.findByLogin(username));
			if (usuario.isPresent()) return JwtUserFactory.create(usuario.get());
			throw new UsernameNotFoundException("Login não encontrado");
		}		
	}*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuarios> usuario = Optional.ofNullable(usuariosRepository.findByEmail(username));			
		if (usuario.isPresent()) return JwtUserFactory.create(usuario.get());
		throw new UsernameNotFoundException("Email não encontrado");
	}
}
