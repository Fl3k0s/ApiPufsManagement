package com.indytek.pufsmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indytek.pufsmanagement.model.Proveedor;
@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
	Optional<Proveedor> findByNombre (String nombre);
	List<Proveedor> findAll();
	
}
