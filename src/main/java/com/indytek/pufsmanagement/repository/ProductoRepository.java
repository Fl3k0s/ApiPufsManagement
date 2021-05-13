package com.indytek.pufsmanagement.repository;

import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.Rango;
import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Producto;
import org.springframework.stereotype.Repository;

/*
repositorio de productos
 */
@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

	Optional<Producto> findById (int id);
	List<Producto> findAll();
	List<Producto> findByRango(Rango rango);
	
}
