package com.fatec.pl.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.pl.modelo.Servico;

public interface RepositorioServico extends JpaRepository<Servico, Long> {

}
