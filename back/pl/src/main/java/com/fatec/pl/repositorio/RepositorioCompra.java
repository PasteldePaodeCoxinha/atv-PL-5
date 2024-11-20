package com.fatec.pl.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.pl.modelo.Compra;

public interface RepositorioCompra extends JpaRepository<Compra, Long> {

}
