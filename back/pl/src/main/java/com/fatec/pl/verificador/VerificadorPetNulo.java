package com.fatec.pl.verificador;

import org.springframework.stereotype.Component;

import com.fatec.pl.modelo.Pet;

@Component
public class VerificadorPetNulo implements Verificador<Pet>{
	@Override
	public boolean verificar(Pet objeto) {
		if(objeto != null) {
			return false;
		}else {
			return true;
		}
	}	
}
