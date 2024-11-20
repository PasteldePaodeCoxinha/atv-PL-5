package com.fatec.pl.atualizador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Produto;
import com.fatec.pl.verificador.VerificadorFloatNula;
import com.fatec.pl.verificador.VerificadorStringNula;

@Component
public class AtualizadorProduto implements Atualizador<Produto> {
	@Autowired
	private VerificadorStringNula verificadorString;
	@Autowired
	private VerificadorFloatNula verificadorFloat;

	@Override
	public void atualizar(Produto alvo, Produto atualizacao) {
		if (!verificadorString.verificar(atualizacao.getNome())) {
			alvo.setNome(atualizacao.getNome());
		}
		
		if (!verificadorFloat.verificar(atualizacao.getPreco())) {
			alvo.setPreco(atualizacao.getPreco());
		}
	}
}