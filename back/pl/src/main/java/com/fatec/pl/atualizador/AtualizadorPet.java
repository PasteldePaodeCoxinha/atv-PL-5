package com.fatec.pl.atualizador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Pet;
import com.fatec.pl.verificador.VerificadorClienteNulo;
import com.fatec.pl.verificador.VerificadorStringNula;

@Component
public class AtualizadorPet implements Atualizador<Pet> {
	@Autowired
	private VerificadorStringNula verificadorString;
	@Autowired
	private VerificadorClienteNulo verificadorCliente;
	@Autowired
	private AtualizadorCliente atualizadorCliente;

	@Override
	public void atualizar(Pet alvo, Pet atualizacao) {
		if (!verificadorString.verificar(atualizacao.getNome())) {
			alvo.setNome(atualizacao.getNome());
		}
		
		if (!verificadorString.verificar(atualizacao.getTipo())) {
			alvo.setTipo(atualizacao.getTipo());
		}
		
		if (!verificadorString.verificar(atualizacao.getRaca())) {
			alvo.setRaca(atualizacao.getRaca());
		}
		
		if (!verificadorString.verificar(atualizacao.getTamanho())) {
			alvo.setTamanho(atualizacao.getTamanho());
		}
		
		if (!verificadorString.verificar(atualizacao.getGenero())) {
			alvo.setGenero(atualizacao.getGenero());
		}
		
		if (!verificadorCliente.verificar(atualizacao.getDono())) {
			if (alvo.getDono() != null) {
				atualizadorCliente.atualizar(alvo.getDono(), atualizacao.getDono());
			} else {
				alvo.setDono(atualizacao.getDono());
			}
		}

	}
}