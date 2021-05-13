package com.indytek.pufsmanagement.repository;

import com.indytek.pufsmanagement.model.Direccion;
import com.indytek.pufsmanagement.model.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/*
repositorio de direcciones
 */
@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Integer> {

	Optional<Direccion> findById (int id);
	
}
