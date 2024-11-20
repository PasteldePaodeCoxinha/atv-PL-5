package com.fatec.pl.atualizador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Servico;
import com.fatec.pl.verificador.VerificadorFloatNula;
import com.fatec.pl.verificador.VerificadorStringNula;

@Component
public class AtualizadorServico implements Atualizador<Servico> {
	@Autowired
	private VerificadorStringNula verificadorString;
	@Autowired
	private VerificadorFloatNula verificadorFloat;

	@Override
	public void atualizar(Servico alvo, Servico atualizacao) {
		if (!verificadorString.verificar(atualizacao.getNome())) {
			alvo.setNome(atualizacao.getNome());
		}
		
		if (!verificadorFloat.verificar(atualizacao.getPreco())) {
			alvo.setPreco(atualizacao.getPreco());
		}
	}
}