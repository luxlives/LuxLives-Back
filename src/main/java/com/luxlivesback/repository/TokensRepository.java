package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxlivesback.model.Tokens;
import com.luxlivesback.model.Usuarios;

public interface TokensRepository extends JpaRepository<Tokens, Usuarios> {
	
}
