package com.indytek.pufsmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Persona;
import com.indytek.pufsmanagement.repository.PersonaRepository;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;

@Service
public class PersonaService implements PersonaServiceI{

	@Autowired private PersonaRepository personaRepo;

	@Override
	public void insertar(Persona persona) {

		personaRepo.save(persona);
		
	}

	@Override
	public boolean borrar(String username) {
		boolean x  = false;
		
		if(buscarPorUsername(username).isPresent()) {
			personaRepo.deleteById(username);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Persona persona) {
		

		return false;
	}

	@Override
	public Optional<Persona> buscarPorUsername(String username) {
		

		return personaRepo.findByUsername(username);
	}
	
}
