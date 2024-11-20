package com.fatec.pl.verificador;

import org.springframework.stereotype.Component;

@Component
public class VerificadorFloatNula implements Verificador<Float> {
	@Override
	public boolean verificar(Float objeto) {
		boolean nulo = true;
		if (!(objeto == null)) {
			nulo = objeto.isNaN();
		}
		return nulo;
	}
}