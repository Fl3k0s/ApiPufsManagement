package com.indytek.pufsmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona, String> {

	Optional<Persona> findByName (String name);
	
}
