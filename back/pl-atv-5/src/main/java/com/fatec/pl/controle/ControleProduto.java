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

import com.fatec.pl.atualizador.AtualizadorProduto;
import com.fatec.pl.hateoas.HateoasProduto;
import com.fatec.pl.modelo.Produto;
import com.fatec.pl.repositorio.RepositorioProduto;

@CrossOrigin
@RestController
public class ControleProduto {
	@Autowired
	private RepositorioProduto repositorio;
	@Autowired
	private HateoasProduto hateoas;
	@Autowired
	private AtualizadorProduto atualizador;

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> obterProduto(@PathVariable Long id) {
		Produto produto = repositorio.findById(id).get();
		if (produto != null) {
			hateoas.adicionarLink(produto);
			return new ResponseEntity<Produto>(produto, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/produto/produtos")
	public ResponseEntity<List<Produto>> obterProdutos() {
		List<Produto> produtos = repositorio.findAll();
		hateoas.adicionarLink(produtos);
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.FOUND);
	}

	@PutMapping("/produto/atualizar")
	public ResponseEntity<?> atualizarProduto(@RequestBody Produto atualizacao) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Produto produto = repositorio.getReferenceById(atualizacao.getId());
		if (produto != null) {
			atualizador.atualizar(produto, atualizacao);
			repositorio.save(produto);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}

	@PostMapping("/produto/cadastrar")
	public ResponseEntity<?> cadastrarProduto(@RequestBody Produto novo) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		if (novo != null) {
			repositorio.save(novo);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}

	@DeleteMapping("/produto/excluir")
	public ResponseEntity<?> excluirProduto(@RequestBody Produto exclusao) {
		Produto produto = repositorio.getReferenceById(exclusao.getId());
		if (produto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			repositorio.delete(produto);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}