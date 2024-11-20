package com.fatec.pl.verificador;

import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Rg;

@Component
public class VerificadorRgNulo implements Verificador<Rg>{
	@Override
	public boolean verificar(Rg objeto) {
		if(objeto != null) {
			return false;
		}else {
			return true;
		}
	}	
}