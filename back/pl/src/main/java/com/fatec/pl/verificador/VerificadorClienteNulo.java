package com.fatec.pl.verificador;

import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Cliente;

@Component
public class VerificadorClienteNulo implements Verificador<Cliente>{
	@Override
	public boolean verificar(Cliente objeto) {
		if(objeto != null) {
			return false;
		}else {
			return true;
		}
	}	
}
