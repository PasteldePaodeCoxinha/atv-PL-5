package com.fatec.pl.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.pl.modelo.Pet;

public interface RepositorioPet extends JpaRepository<Pet, Long> {
}
