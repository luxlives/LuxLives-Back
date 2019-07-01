package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxlivesback.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
