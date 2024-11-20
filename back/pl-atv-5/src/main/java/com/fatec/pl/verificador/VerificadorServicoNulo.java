package com.fatec.pl.verificador;

import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Servico;

@Component
public class VerificadorServicoNulo implements Verificador<Servico>{
	@Override
	public boolean verificar(Servico objeto) {
		if(objeto != null) {
			return false;
		}else {
			return true;
		}
	}	
}
