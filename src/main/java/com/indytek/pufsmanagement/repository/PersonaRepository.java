package com.indytek.pufsmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Persona;
import org.springframework.stereotype.Repository;

/*
repositorio de personas
 */
@Repository
public interface PersonaRepository extends CrudRepository<Persona, String> {

	Optional<Persona> findByName (String name);
	Optional<Persona> findByEmail (String email);
	Optional<Persona> findByDni (String dni);
	
}
