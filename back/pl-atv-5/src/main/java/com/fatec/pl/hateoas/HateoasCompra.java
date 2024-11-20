package com.fatec.pl.hateoas;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.fatec.pl.controle.ControleCompra;
import com.fatec.pl.modelo.Compra;

@Component
public class HateoasCompra implements Hateoas<Compra> {

	@Override
	public void adicionarLink(List<Compra> lista) {
		for (Compra compra : lista) {
			long id = compra.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ControleCompra.class).obterCompra(id)).withSelfRel();
			compra.add(linkProprio);
		}
	}

	@Override
	public void adicionarLink(Compra objeto) {
		Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ControleCompra.class).obterCompras())
				.withRel("compras");
		objeto.add(linkProprio);

	}
}