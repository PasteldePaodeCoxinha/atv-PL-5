package com.fatec.pl.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.pl.atualizador.AtualizadorCompra;
import com.fatec.pl.hateoas.HateoasCompra;
import com.fatec.pl.modelo.Compra;
import com.fatec.pl.repositorio.RepositorioCompra;

@CrossOrigin
@RestController
public class ControleCompra {
	@Autowired
	private RepositorioCompra repositorio;
	@Autowired
	private HateoasCompra hateoas;
	@Autowired
	private AtualizadorCompra atualizador;

	@GetMapping("/compra/{id}")
	public ResponseEntity<Compra> obterCompra(@PathVariable Long id) {
		Compra compra = repositorio.findById(id).get();
		if (compra != null) {
			hateoas.adicionarLink(compra);
			return new ResponseEntity<Compra>(compra, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Compra>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/compra/compras")
	public ResponseEntity<List<Compra>> obterCompras() {
		List<Compra> compras = repositorio.findAll();
		hateoas.adicionarLink(compras);
		return new ResponseEntity<List<Compra>>(compras, HttpStatus.FOUND);
	}

	@PutMapping("/compra/atualizar")
	public ResponseEntity<?> atualizarCompra(@RequestBody Compra atualizacao) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Compra compra = repositorio.getReferenceById(atualizacao.getId());
		if (compra != null) {
			atualizador.atualizar(compra, atualizacao);
			repositorio.save(compra);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}

	@PostMapping("/compra/cadastrar")
	public ResponseEntity<?> cadastrarCompra(@RequestBody Compra novo) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		if (novo != null) {
			repositorio.save(novo);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/compra/excluir")
	public ResponseEntity<?> excluirCompra(@RequestBody Compra exclusao) {
		Compra compra = repositorio.getReferenceById(exclusao.getId());
		if (compra == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repositorio.delete(compra);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}