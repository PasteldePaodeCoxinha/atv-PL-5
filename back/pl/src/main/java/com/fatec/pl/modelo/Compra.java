package com.fatec.pl.modelo;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Compra extends RepresentationModel<Compra> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Cliente cliente;
	
	@Column
	private Pet pet;
	
	@Column
	private String tipo;
	
	@Column
	private Produto compradoPro;
	
	@Column
	private Servico compradoSer;
}
