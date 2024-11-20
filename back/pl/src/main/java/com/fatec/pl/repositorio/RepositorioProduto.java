package com.fatec.pl.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.pl.modelo.Produto;

public interface RepositorioProduto extends JpaRepository<Produto, Long>{
}
