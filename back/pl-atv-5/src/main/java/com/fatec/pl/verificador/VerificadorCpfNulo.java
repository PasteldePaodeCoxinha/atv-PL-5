package com.fatec.pl.verificador;

import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Cpf;

@Component
public class VerificadorCpfNulo implements Verificador<Cpf>{
	@Override
	public boolean verificar(Cpf objeto) {
		if(objeto != null) {
			return false;
		}else {
			return true;
		}
	}	
}
