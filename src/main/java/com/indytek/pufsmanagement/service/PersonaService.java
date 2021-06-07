package com.indytek.pufsmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Persona;
import com.indytek.pufsmanagement.repository.PersonaRepository;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;

@Service
/*
servicio de personas

los metodos que son llamados desde el controlador de personas se encuentran aqui
 */
public class PersonaService implements PersonaServiceI{

	@Autowired private PersonaRepository personaRepo;

	@Override
	public void insertar(Persona persona) {

		personaRepo.save(persona);
		
	}

	@Override
	public boolean borrar(String name) {
		boolean x  = false;
		
		if(buscarPorName(name).isPresent()) {
			personaRepo.deleteById(name);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Persona persona) {
		

		return false;
	}

	@Override
	public Optional<Persona> buscarPorId(int id) {
		return personaRepo.findById(id);
	}

	@Override
	public Optional<Persona> buscarPorName(String name) {
		

		return personaRepo.findByName(name);
	}

	@Override
	public Optional<Persona> buscarPorEmail(String email) {

		return personaRepo.findByEmail(email);
	}

	@Override
	public Optional<Persona> buscarPorDni(String dni) {
		return personaRepo.findByDni(dni);
	}


}
