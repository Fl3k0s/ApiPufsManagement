package com.indytek.pufsmanagement.repository;

import com.indytek.pufsmanagement.model.Cliente;
import com.indytek.pufsmanagement.model.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, String> {

	Optional<Cliente> findByName (String name);
	
}
