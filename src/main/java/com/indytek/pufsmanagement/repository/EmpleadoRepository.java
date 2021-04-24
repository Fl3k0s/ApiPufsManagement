package com.indytek.pufsmanagement.repository;

import com.indytek.pufsmanagement.model.Empleado;
import com.indytek.pufsmanagement.model.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado, String> {

	Optional<Empleado> findByName (String name);
	
}
