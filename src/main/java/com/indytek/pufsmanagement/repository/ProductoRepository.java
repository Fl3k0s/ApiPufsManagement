package com.indytek.pufsmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

	Optional<Producto> findById (int id);
	
}
