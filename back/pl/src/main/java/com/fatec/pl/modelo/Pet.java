package com.fatec.pl.modelo;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pet extends RepresentationModel<Pet> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String tipo;
	
	@Column
	private String raca;
	
	@Column
	private String tamanho;
	
	@Column
	private String genero;
	
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	private Cliente dono;
}
