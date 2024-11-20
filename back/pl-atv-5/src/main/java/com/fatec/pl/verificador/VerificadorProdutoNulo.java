package com.fatec.pl.verificador;

import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Produto;

@Component
public class VerificadorProdutoNulo implements Verificador<Produto>{
	@Override
	public boolean verificar(Produto objeto) {
		if(objeto != null) {
			return false;
		}else {
			return true;
		}
	}	
}
