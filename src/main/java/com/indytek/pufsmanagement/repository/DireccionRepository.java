package com.indytek.pufsmanagement.repository;

import com.indytek.pufsmanagement.model.Direccion;
import com.indytek.pufsmanagement.model.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
/*
repositorio de direcciones
 */
public interface DireccionRepository extends CrudRepository<Direccion, Integer> {

	Optional<Direccion> findById (int id);
	
}
