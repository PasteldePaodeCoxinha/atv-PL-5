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
public class Compra extends RepresentationModel<Compra> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	private Pet pet;
	
	@Column
	private String tipo;
	
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	private Produto compradoPro;
	
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	private Servico compradoSer;
}
