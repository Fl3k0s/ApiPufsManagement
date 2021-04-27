package com.indytek.pufsmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Producto;
/*
repositorio de productos
 */
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

	Optional<Producto> findById (int id);
	List<Producto> findAll();
	
}
