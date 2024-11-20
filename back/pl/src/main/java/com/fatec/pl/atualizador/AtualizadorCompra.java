package com.fatec.pl.atualizador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Compra;
import com.fatec.pl.verificador.VerificadorClienteNulo;
import com.fatec.pl.verificador.VerificadorPetNulo;
import com.fatec.pl.verificador.VerificadorProdutoNulo;
import com.fatec.pl.verificador.VerificadorServicoNulo;
import com.fatec.pl.verificador.VerificadorStringNula;

@Component
public class AtualizadorCompra implements Atualizador<Compra> {
	@Autowired
	private VerificadorStringNula verificadorString;
	@Autowired
	private VerificadorClienteNulo verificadorCliente;
	@Autowired
	private VerificadorPetNulo verificadorPet;
	@Autowired
	private VerificadorProdutoNulo verificadorProduto;
	@Autowired
	private VerificadorServicoNulo verificadorServico;
	@Autowired
	private AtualizadorCliente atualizadorCliente;
	@Autowired
	private AtualizadorPet atualizadorPet;
	@Autowired
	private AtualizadorProduto atualizadorProduto;
	@Autowired
	private AtualizadorServico atualizadorServico;

	@Override
	public void atualizar(Compra alvo, Compra atualizacao) {
		
		if (!verificadorCliente.verificar(atualizacao.getCliente())) {
			if (alvo.getCliente() != null) {
				atualizadorCliente.atualizar(alvo.getCliente(), atualizacao.getCliente());
			} else {
				alvo.setCliente(atualizacao.getCliente());
			}
		}
		
		if (!verificadorPet.verificar(atualizacao.getPet())) {
			if (alvo.getPet() != null) {
				atualizadorPet.atualizar(alvo.getPet(), atualizacao.getPet());
			} else {
				alvo.setPet(atualizacao.getPet());
			}
		}
		
		if (!verificadorString.verificar(atualizacao.getTipo())) {
			alvo.setTipo(atualizacao.getTipo());
		}
		
		if (!verificadorProduto.verificar(atualizacao.getCompradoPro())) {
			if (alvo.getCompradoPro() != null) {
				atualizadorProduto.atualizar(alvo.getCompradoPro(), atualizacao.getCompradoPro());
			} else {
				alvo.setCompradoPro(null);
			}
		}
		
		if (!verificadorServico.verificar(atualizacao.getCompradoSer())) {
			if (alvo.getCompradoSer() != null) {
				atualizadorServico.atualizar(alvo.getCompradoSer(), atualizacao.getCompradoSer());
			} else {
				alvo.setCompradoSer(atualizacao.getCompradoSer());
			}
		}
	}
}