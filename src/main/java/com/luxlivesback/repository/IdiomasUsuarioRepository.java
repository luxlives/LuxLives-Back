package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.IdiomasUsuario;

@Transactional(readOnly = true)
public interface IdiomasUsuarioRepository extends JpaRepository<IdiomasUsuario, Long> {
}
